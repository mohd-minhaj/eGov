/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
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
 */

package org.egov.works.letterofacceptance.entity;

import java.util.Date;

public class SearchRequestLetterOfAcceptance {

    private String workOrderNumber;
    private Date fromDate;
    private Date toDate;
    private String name;
    private String fileNumber;
    private String mbRefNumber;
    private Long departmentName;
    private String estimateNumber;
    private String egwStatus;
    private String workIdentificationNumber;
    private Date adminSanctionFromDate;
    private Date adminSanctionToDate;
    private Long typeOfWork;
    private Long subTypeOfWork;
    private String contractor;
    private String contractorName;
    private Long workAssignedTo;

    public SearchRequestLetterOfAcceptance() {
    }

    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(final String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(final Date toDate) {
        this.toDate = toDate;
    }

    public String getEstimateNumber() {
        return estimateNumber;
    }

    public void setEstimateNumber(final String estimateNumber) {
        this.estimateNumber = estimateNumber;
    }

    public Long getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(final Long departmentName) {
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEgwStatus() {
        return egwStatus;
    }

    public void setEgwStatus(final String egwStatus) {
        this.egwStatus = egwStatus;
    }

    public String getWorkIdentificationNumber() {
        return workIdentificationNumber;
    }

    public void setWorkIdentificationNumber(final String workIdentificationNumber) {
        this.workIdentificationNumber = workIdentificationNumber;
    }

    public Date getAdminSanctionFromDate() {
        return adminSanctionFromDate;
    }

    public void setAdminSanctionFromDate(final Date adminSanctionFromDate) {
        this.adminSanctionFromDate = adminSanctionFromDate;
    }

    public Date getAdminSanctionToDate() {
        return adminSanctionToDate;
    }

    public void setAdminSanctionToDate(final Date adminSanctionToDate) {
        this.adminSanctionToDate = adminSanctionToDate;
    }

    public Long getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(final Long typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Long getSubTypeOfWork() {
        return subTypeOfWork;
    }

    public void setSubTypeOfWork(final Long subTypeOfWork) {
        this.subTypeOfWork = subTypeOfWork;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(final String contractor) {
        this.contractor = contractor;
    }

    public String getMbRefNumber() {
        return mbRefNumber;
    }

    public void setMbRefNumber(final String mbRefNumber) {
        this.mbRefNumber = mbRefNumber;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(final String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(final String contractorName) {
        this.contractorName = contractorName;
    }

    public Long getWorkAssignedTo() {
        return workAssignedTo;
    }

    public void setWorkAssignedTo(final Long workAssignedTo) {
        this.workAssignedTo = workAssignedTo;
    }
}
