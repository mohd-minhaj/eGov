package org.egov.edcr.rule;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.edcr.entity.PlanDetail;
import org.egov.edcr.entity.Result;
import org.egov.edcr.entity.RuleOutput;
import org.egov.edcr.entity.SubRuleOutput;
import org.egov.edcr.entity.measurement.CulDeSacRoad;
import org.egov.edcr.entity.measurement.Lane;
import org.egov.edcr.entity.measurement.NonNotifiedRoad;
import org.egov.edcr.entity.measurement.NotifiedRoad;
import org.egov.edcr.entity.utility.RuleReportOutput;
import org.egov.edcr.service.ReportService;
import org.egov.edcr.utility.DcrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJDataSource;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.Subreport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class Rule26 extends GeneralRule {
    private static final BigDecimal three = BigDecimal.valueOf(3);
    private static final BigDecimal a = BigDecimal.valueOf(1.8);
    private static final String SUB_RULE_26_DESCRIPTION = "Prohibition for constructions abutting public roads.";
    private static final String SUB_RULE_26 = "Rule 26";
    private static final String SUB_RULE_26A = "26A";
    private static final String SUB_RULE_26A_DESCRIPTION = "Waste Disposal";

    @Autowired
    private ReportService reportService;

    @Override
    public PlanDetail validate(PlanDetail planDetail) {
        HashMap<String, String> errors = new HashMap<>();
        System.out.println("validate 26");
        if (planDetail != null) {

            // TODO: CHECK WHETHER ANY ROAD SHOULD PASS THROUGH SITE IS MANDATORY ??????

            // If either notified or non notified road width not defined, then show error.
            if ((planDetail.getNotifiedRoads() == null || planDetail.getNonNotifiedRoads() == null) &&
                    !(planDetail.getNotifiedRoads().size() > 0 ||
                            planDetail.getNonNotifiedRoads().size() > 0)) {
                errors.put(DcrConstants.ROAD,
                        prepareMessage(DcrConstants.OBJECTNOTDEFINED, DcrConstants.ROAD));
                planDetail.addErrors(errors);
            }
            if (planDetail.getNotifiedRoads() != null &&
                    planDetail.getNotifiedRoads().size() > 0)
                for (NotifiedRoad notifiedRoad : planDetail.getNotifiedRoads())
                    if (notifiedRoad.getShortestDistanceToRoad() == null ||
                            notifiedRoad.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) <= 0) {
                        errors.put(DcrConstants.NOTIFIED_SHORTESTDISTINCTTOROAD,
                                prepareMessage(DcrConstants.OBJECTNOTDEFINED, "Notified " + DcrConstants.SHORTESTDISTINCTTOROAD));
                        planDetail.addErrors(errors);
                    }
            if (planDetail.getNonNotifiedRoads() != null &&
                    planDetail.getNonNotifiedRoads().size() > 0)
                for (NonNotifiedRoad nonNotifiedRoad : planDetail.getNonNotifiedRoads())
                    if (nonNotifiedRoad.getShortestDistanceToRoad() == null ||
                            nonNotifiedRoad.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) <= 0) {
                        errors.put(DcrConstants.NONNOTIFIED_SHORTESTDISTINCTTOROAD,
                                prepareMessage(DcrConstants.OBJECTNOTDEFINED,
                                        "Non Notified " + DcrConstants.SHORTESTDISTINCTTOROAD));
                        planDetail.addErrors(errors);
                    }
            if (planDetail.getLaneRoads() != null &&
                    planDetail.getLaneRoads().size() > 0)
                for (Lane laneRoad : planDetail.getLaneRoads())
                    if (laneRoad.getShortestDistanceToRoad() == null ||
                            laneRoad.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) <= 0) {
                        errors.put(DcrConstants.LANE_SHORTESTDISTINCTTOROAD,
                                prepareMessage(DcrConstants.OBJECTNOTDEFINED, "Lane " + DcrConstants.SHORTESTDISTINCTTOROAD));
                        planDetail.addErrors(errors);
                    }
            if (planDetail.getCuldeSacRoads() != null &&
                    planDetail.getCuldeSacRoads().size() > 0)
                for (CulDeSacRoad culdSac : planDetail.getCuldeSacRoads())
                    if (culdSac.getShortestDistanceToRoad() == null ||
                            culdSac.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) <= 0) {
                        errors.put(DcrConstants.CULD_SAC_SHORTESTDISTINCTTOROAD,
                                prepareMessage(DcrConstants.OBJECTNOTDEFINED,
                                        "Cul-de-sac " + DcrConstants.SHORTESTDISTINCTTOROAD));
                        planDetail.addErrors(errors);
                    }
        }

        if (planDetail != null && planDetail.getBuilding() != null)
            // waste disposal defined or not
            if (planDetail.getUtility().getWasteDisposalUnits().isEmpty()) {
            errors.put(DcrConstants.WASTEDISPOSAL,
                    prepareMessage(DcrConstants.OBJECTNOTDEFINED, DcrConstants.WASTEDISPOSAL));
            planDetail.addErrors(errors);
            }

        return planDetail;
    }

    private String prepareMessage(String code, String args) {
        return edcrMessageSource.getMessage(code,
                new String[] { args }, LocaleContextHolder.getLocale());
        // return code+" "+args;
    }

    @Override
    public PlanDetail process(PlanDetail planDetail) {
        // TODO: NEED TO ADD APPLICABLE NOT APPLICABLE.. IRRESPECTIVE OF DATA PROVIDED or not.
        rule26(planDetail);
        rule26A(planDetail);
        return planDetail;
    }

    private void rule26(PlanDetail planDetail) {
        // If both roads are not defined.
        if (planDetail.getNotifiedRoads() != null &&
                !(planDetail.getNotifiedRoads().size() > 0 ||
                        planDetail.getNonNotifiedRoads().size() > 0 ||
                        planDetail.getCuldeSacRoads().size() > 0 ||
                        planDetail.getLaneRoads().size() > 0))
            planDetail.reportOutput
                    .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                            DcrConstants.ROAD,
                            null,
                            null,
                            Result.Not_Accepted, DcrConstants.ROAD + DcrConstants.OBJECTNOTDEFINED_DESC));

        else if (planDetail.getNotifiedRoads() != null &&
                planDetail.getNotifiedRoads().size() > 0)
            for (NotifiedRoad notifiedRoad : planDetail.getNotifiedRoads())
                if (notifiedRoad.getShortestDistanceToRoad() != null &&
                        notifiedRoad.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) > 0)
                    if (notifiedRoad.getShortestDistanceToRoad().compareTo(three) >= 0)
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.NOTIFIED_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        notifiedRoad.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Accepted, null));
                    else
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.NOTIFIED_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        notifiedRoad.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Not_Accepted, null));

        // If non notified road present then check 3 mts distance should maintain
        if (planDetail.getNonNotifiedRoads() != null &&
                planDetail.getNonNotifiedRoads().size() > 0)
            for (NonNotifiedRoad nonNotifiedRoad : planDetail.getNonNotifiedRoads())
                if (nonNotifiedRoad.getShortestDistanceToRoad() != null &&
                        nonNotifiedRoad.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) > 0)
                    if (nonNotifiedRoad.getShortestDistanceToRoad().compareTo(three) >= 0)
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.NONNOTIFIED_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        nonNotifiedRoad.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Accepted, null));
                    else
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.NONNOTIFIED_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        nonNotifiedRoad.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Not_Accepted, null));

        // If culd_sac road present then check 3 mts distance should maintain
        if (planDetail.getCuldeSacRoads() != null &&
                planDetail.getCuldeSacRoads().size() > 0)
            for (CulDeSacRoad culd_sac : planDetail.getCuldeSacRoads())
                if (culd_sac.getShortestDistanceToRoad() != null &&
                        culd_sac.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) > 0)
                    if (culd_sac.getShortestDistanceToRoad().compareTo(three) >= 0)
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.CULD_SAC_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        culd_sac.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Accepted, null));
                    else
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.CULD_SAC_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        culd_sac.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Not_Accepted, null));

        // If Lane road present then check 3 mts distance should maintain
        if (planDetail.getLaneRoads() != null &&
                planDetail.getLaneRoads().size() > 0)
            for (Lane lane : planDetail.getLaneRoads())
                if (lane.getShortestDistanceToRoad() != null &&
                        lane.getShortestDistanceToRoad().compareTo(BigDecimal.ZERO) > 0)
                    if (lane.getShortestDistanceToRoad().compareTo(three) >= 0)
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.LANE_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        lane.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Accepted, null));
                    else
                        planDetail.reportOutput
                                .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26, SUB_RULE_26_DESCRIPTION,
                                        DcrConstants.LANE_SHORTESTDISTINCTTOROAD,
                                        "Min. " + three.toString() + DcrConstants.IN_METER,
                                        lane.getShortestDistanceToRoad().toString()
                                                + DcrConstants.IN_METER,
                                        Result.Not_Accepted, null));
    }

    private void rule26A(PlanDetail planDetail) {
        if (planDetail.getUtility().getWasteDisposalUnits().size() > 0)

            planDetail.reportOutput
                    .add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26A, SUB_RULE_26A_DESCRIPTION,
                            DcrConstants.WASTEDISPOSAL,
                            null,
                            null,
                            Result.Accepted, DcrConstants.WASTEDISPOSAL + DcrConstants.OBJECTDEFINED_DESC));

        else
            planDetail.reportOutput.add(buildRuleOutputWithSubRule(DcrConstants.RULE26, SUB_RULE_26A, SUB_RULE_26A_DESCRIPTION,
                    DcrConstants.WASTEDISPOSAL,
                    null,
                    null,
                    Result.Not_Accepted, DcrConstants.WASTEDISPOSAL + DcrConstants.OBJECTNOTDEFINED_DESC));

    }

    @Override
    public boolean generateRuleReport(PlanDetail planDetail, FastReportBuilder drb2, Map valuesMap, boolean reportStatus) {
        List<RuleOutput> rules = planDetail.getReportOutput().getRuleOutPuts();
        for (RuleOutput ruleOutput : rules)
            if (ruleOutput.getKey().equalsIgnoreCase(DcrConstants.RULE26)) {
                FastReportBuilder drb = new FastReportBuilder();
                StringBuilder stringBuilder = new StringBuilder();
                if (ruleOutput.getMessage() != null)
                    stringBuilder.append("Message : ").append(ruleOutput.getMessage()).append("\\n");
                if (ruleOutput.getRuleDescription() != null)
                    stringBuilder.append("Description : ").append(ruleOutput.getRuleDescription()).append("\\n");
                drb.setMargins(5, 0, 10, 10);
                drb.setTitle("Rule : " + ruleOutput.getKey() + "\\n")
                        .setSubtitle(stringBuilder.toString())
                        .setPrintBackgroundOnOddRows(false).setWhenNoData("", null)
                        .setTitleStyle(reportService.getTitleStyle())
                        .setSubtitleStyle(reportService.getSubTitleStyle())
                        .setSubtitleHeight(30);

                new JRBeanCollectionDataSource(ruleOutput.getSubRuleOutputs());
                final DJDataSource djds = new DJDataSource(ruleOutput.getKey(), DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
                        DJConstants.DATA_SOURCE_TYPE_JRDATASOURCE);

                final Subreport subRep = new Subreport();
                subRep.setLayoutManager(new ClassicLayoutManager());
                subRep.setDynamicReport(drb.build());
                subRep.setDatasource(djds);
                subRep.setUseParentReportParameters(true);
                subRep.setSplitAllowed(true);
                drb2.addConcatenatedReport(subRep);
                SubRuleOutput subRule26 = new SubRuleOutput();
                SubRuleOutput subRule26A = new SubRuleOutput();
                List<RuleReportOutput> subRule26Output = new ArrayList();
                List<RuleReportOutput> subRule26AOutput = new ArrayList();
                valuesMap.put(ruleOutput.getKey(), new JRBeanCollectionDataSource(ruleOutput.getSubRuleOutputs()));
                if (ruleOutput != null && !ruleOutput.getSubRuleOutputs().isEmpty())
                    try {
                        for (SubRuleOutput subRuleOutput : ruleOutput.getSubRuleOutputs()) {
                            reportStatus = reportService.getReportStatus(subRuleOutput.getRuleReportOutputs(), reportStatus);
                            if (subRuleOutput.getKey().equalsIgnoreCase(SUB_RULE_26)) {
                                subRule26.setKey(subRuleOutput.getKey());
                                subRule26.setMessage(subRuleOutput.getMessage());
                                subRule26.setRuleDescription(subRuleOutput.getRuleDescription());
                                subRule26.getRuleReportOutputs().addAll(subRuleOutput.getRuleReportOutputs());
                            } else {
                                subRule26A.setKey(subRuleOutput.getKey());
                                subRule26A.setMessage(subRuleOutput.getMessage());
                                subRule26A.setRuleDescription(subRuleOutput.getRuleDescription());

                            }
                        }
                        if(subRule26 != null) {
                            valuesMap.put(SUB_RULE_26 + "DataSource",
                                    new JRBeanCollectionDataSource(subRule26.getRuleReportOutputs()));
                            drb2.addConcatenatedReport(generateSubRuleReport(subRule26, drb2, valuesMap));
                        }
                        if(subRule26A != null) {
                            valuesMap.put(SUB_RULE_26A + "DataSource",
                                    new JRBeanCollectionDataSource(ruleOutput.getSubRuleOutputs()));
                            drb2.addConcatenatedReport(generateSubRuleReport(subRule26A, drb2, valuesMap));
                        }
                      
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
            }
        return reportStatus;
    }

    public Subreport generateSubRuleReport(final SubRuleOutput subRuleOutput, FastReportBuilder drb2, Map valuesMap)
            throws JRException, IOException, Exception {
        FastReportBuilder drb = new FastReportBuilder();
        final Style columnStyle = reportService.getColumnStyle();
        final Style columnHeaderStyle = reportService.getColumnHeaderStyle();
        final Style verifiedColumnStyle = reportService.getVerifiedColumnStyle();
        StringBuilder stringBuilder = new StringBuilder();
        if (subRuleOutput.getMessage() != null)
            stringBuilder.append("Message : ").append(subRuleOutput.getMessage()).append("\\n");
        if (subRuleOutput.getRuleDescription() != null)
            stringBuilder.append("Description : ").append(subRuleOutput.getRuleDescription()).append("\\n");

        drb.setMargins(0, 10, 10, 10);
        drb.setTitle("SubRule : " + subRuleOutput.getKey())
                .setSubtitle(stringBuilder.toString())
                .setPrintBackgroundOnOddRows(false).setWhenNoData("", null)
                .setTitleStyle(reportService.getTitleStyle())
                .setSubtitleStyle(reportService.getSubTitleStyle())
                .setSubtitleHeight(30).setTitleHeight(40);

        if (subRuleOutput.getRuleReportOutputs() != null && !subRuleOutput.getRuleReportOutputs().isEmpty()) {

            drb.addColumn("Field Verified", "fieldVerified", String.class.getName(), 120, verifiedColumnStyle, columnHeaderStyle,
                    true);
            drb.addColumn("Expected Result", "expectedResult", String.class.getName(), 120, columnStyle, columnHeaderStyle, true);
            drb.addColumn("Actual Result", "actualResult", String.class.getName(), 120, columnStyle, columnHeaderStyle);
            drb.addColumn("Status", "status", String.class.getName(), 120, columnStyle, columnHeaderStyle);
        }

        new JRBeanCollectionDataSource(subRuleOutput.getRuleReportOutputs());
        final DJDataSource djds = new DJDataSource(subRuleOutput.getKey() + "DataSource",
                DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
                DJConstants.DATA_SOURCE_TYPE_JRDATASOURCE);

        final Subreport subRep = new Subreport();
        subRep.setLayoutManager(new ClassicLayoutManager());
        subRep.setDynamicReport(drb.build());
        subRep.setDatasource(djds);
        subRep.setUseParentReportParameters(true);
        subRep.setSplitAllowed(true);
        return subRep;
    }

}