/*
 *    eGov  SmartCity eGovernance suite aims to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) 2017  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *            Further, all user interfaces, including but not limited to citizen facing interfaces,
 *            Urban Local Bodies interfaces, dashboards, mobile applications, of the program and any
 *            derived works should carry eGovernments Foundation logo on the top right corner.
 *
 *            For the logo, please refer http://egovernments.org/html/logo/egov_logo.png.
 *            For any further queries on attribution, including queries on brand guidelines,
 *            please contact contact@egovernments.org
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 *
 */

package org.egov.pgr.service;

import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.service.BoundaryService;
import org.egov.infra.config.persistence.datasource.routing.annotation.ReadOnly;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.infra.reporting.engine.ReportRequest;
import org.egov.infra.reporting.engine.ReportService;
import org.egov.pgr.entity.Complaint;
import org.egov.pgr.entity.ComplaintRouter;
import org.egov.pgr.entity.ComplaintType;
import org.egov.pgr.entity.contract.BulkRouterGenerator;
import org.egov.pgr.entity.contract.ComplaintRouterSearchRequest;
import org.egov.pgr.repository.ComplaintRouterRepository;
import org.egov.pgr.repository.specs.ComplaintRouterSpec;
import org.egov.pims.commons.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComplaintRouterService {

    @Autowired
    private ComplaintRouterRepository complaintRouterRepository;

    @Autowired
    private BoundaryService boundaryService;

    @Autowired
    private ReportService reportService;

    public Position getComplaintAssignee(Complaint complaint) {
        ComplaintRouter complaintRouter = getComplaintRouter(complaint);
        if (complaintRouter == null)
            throw new ApplicationRuntimeException("PGR.001");
        else
            return complaintRouter.getPosition();
    }

    @Transactional
    public ComplaintRouter createComplaintRouter(ComplaintRouter complaintRouter) {
        return complaintRouterRepository.save(complaintRouter);
    }

    @Transactional
    public ComplaintRouter updateComplaintRouter(ComplaintRouter complaintRouter) {
        return complaintRouterRepository.save(complaintRouter);
    }

    @Transactional
    public void deleteComplaintRouter(ComplaintRouter complaintRouter) {
        complaintRouterRepository.delete(complaintRouter);
    }

    @Transactional
    public void createBulkRouter(BulkRouterGenerator bulkRouterGenerator) {
        for (ComplaintType complaintType : bulkRouterGenerator.getComplaintTypes()) {
            for (Boundary boundary : bulkRouterGenerator.getBoundaries()) {
                ComplaintRouter router = new ComplaintRouter();
                router.setComplaintType(complaintType);
                router.setBoundary(boundary);
                router.setPosition(bulkRouterGenerator.getPosition());
                ComplaintRouter existingRouter = getExistingRouter(router);
                if (existingRouter == null) {
                    createComplaintRouter(router);
                } else {
                    existingRouter.setPosition(bulkRouterGenerator.getPosition());
                    updateComplaintRouter(existingRouter);
                }
            }
        }
    }

    public Boolean validateRouter(ComplaintRouter complaintRouter) {
        return getExistingRouter(complaintRouter) != null;
    }

    public ComplaintRouter getExistingRouter(ComplaintRouter complaintRouter) {
        ComplaintRouter existingRouter = null;
        if (complaintRouter.getComplaintType() != null && complaintRouter.getBoundary() != null)
            existingRouter = complaintRouterRepository.findByComplaintTypeAndBoundary(complaintRouter.getComplaintType(),
                    complaintRouter.getBoundary());
        else if (complaintRouter.getComplaintType() != null && complaintRouter.getBoundary() == null)
            existingRouter = complaintRouterRepository.findByOnlyComplaintType(complaintRouter.getComplaintType());
        else if (complaintRouter.getComplaintType() == null && complaintRouter.getBoundary() != null)
            existingRouter = complaintRouterRepository.findByOnlyBoundary(complaintRouter.getBoundary());
        return existingRouter;
    }

    public ComplaintRouter getRouterById(Long id) {
        return complaintRouterRepository.findOne(id);
    }

    @ReadOnly
    public Page<ComplaintRouter> getComplaintRouter(ComplaintRouterSearchRequest routerSearchRequest) {
        Pageable pageable = new PageRequest(routerSearchRequest.pageNumber(),
                routerSearchRequest.pageSize(),
                routerSearchRequest.orderDir(), routerSearchRequest.orderBy());
        return complaintRouterRepository
                .findAll(ComplaintRouterSpec.search(routerSearchRequest), pageable);
    }

    @ReadOnly
    public ReportOutput generateRouterReport(ComplaintRouterSearchRequest reportCriteria) {
        ReportRequest reportRequest = new ReportRequest("pgr_routerView",
                complaintRouterRepository.findAll(ComplaintRouterSpec.search(reportCriteria)));
        reportRequest.setReportFormat(reportCriteria.getPrintFormat());
        return reportService.createReport(reportRequest);
    }

    public List<ComplaintRouter> getRoutersByComplaintTypeBoundary(List<ComplaintType> complaintTypes,
                                                                   List<Boundary> boundaries) {
        return complaintRouterRepository.findRoutersByComplaintTypesBoundaries(complaintTypes, boundaries);
    }

    public ComplaintRouter getComplaintRouter(Complaint complaint) {
        ComplaintRouter complaintRouter;
        if (complaint.getLocation() == null) {
            complaintRouter = complaintRouterRepository.findByOnlyComplaintType(complaint.getComplaintType());
            if (complaintRouter == null)
                complaintRouter = complaintRouterRepository.findCityAdminGrievanceOfficer("ADMINISTRATION");

        } else {
            complaintRouter = complaintRouterRepository
                    .findByComplaintTypeAndBoundary(complaint.getComplaintType(), complaint.getLocation());
            if (complaintRouter == null) {
                List<Boundary> boundaries = boundaryService.getBoundaryWithItsAncestors(complaint.getLocation().getId());
                complaintRouter = getComplaintRouterByComplaintTypeAndBoundaries(complaint.getComplaintType(), boundaries);
                if (complaintRouter == null)
                    complaintRouter = complaintRouterRepository.findByOnlyComplaintType(complaint.getComplaintType());
                if (complaintRouter == null)
                    complaintRouter = getComplaintRouterByBoundaries(boundaries);
            }
        }
        return complaintRouter;
    }

    public ComplaintRouter getComplaintRouterByComplaintTypeAndBoundaries(ComplaintType complaintType, List<Boundary> boundaries) {
        ComplaintRouter complaintRouter = null;
        for (Boundary boundary : boundaries) {
            complaintRouter = complaintRouterRepository
                    .findByComplaintTypeAndBoundary(complaintType, boundary);
            if (complaintRouter != null)
                break;
        }

        return complaintRouter;
    }

    public ComplaintRouter getComplaintRouterByBoundaries(List<Boundary> boundaries) {
        ComplaintRouter complaintRouter = null;
        for (Boundary boundary : boundaries) {
            complaintRouter = complaintRouterRepository.findByOnlyBoundary(boundary);
            if (complaintRouter != null)
                break;
        }
        return complaintRouter;
    }
}
