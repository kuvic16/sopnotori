package net.brac.bep.service.impl;

import net.brac.bep.data.repository.ExpenditureRepository;
import net.brac.bep.data.repository.UdvRepository;
import net.brac.bep.rest.model.FinancialReportModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.UdvModel;
import net.brac.bep.service.FinancialReportService;
import net.brac.bep.service.UdvService;
import net.brac.bep.service.UtilityService;
import net.brac.bep.util.IUtil;
import net.brac.bep.util.RestUtil;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @File ReportServiceImp.java: ReportServiceImp Implementation for reportService
 * @CreationDate 10 May, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class FinancialReportServiceImp implements FinancialReportService {

    @Inject
    ExpenditureRepository expenditureRepository;
    @Inject
    UtilityService utilityService;
    @Inject
    UdvRepository udvRepository;
    @Inject
    UdvService udvService;


    @Override
    public ResponseModel create(FinancialReportModel model) {

        return null;
    }

    public ResponseModel update(String id, FinancialReportModel model) {

        return null;
    }

    @Override
    public ResponseModel findById(String id) {
       /* ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_DETAILS);
        try {
            Expenditure expenditure = expenditureRepository.findById(id);
            response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new FinancialReportModel(expenditure));
        } catch (Throwable t) {
            response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
        }*/
        return null;
    }

    @Override
    public ResponseModel listAll(Integer startPosition, Integer maxResult) {
        return null;
    }


    // The main method of report generation
    @Override
    public ResponseModel listAll(Integer startPosition, Integer maxResult,
                                 String expenditureTypeUdvId,
                                 String headsOfAccountUdvId,
                                 String locationId,
                                 String locationHierarchy,
                                 Integer year,
                                 Integer month,
                                 Integer totalNumberOfColumn)
    {
        ResponseModel response = new ResponseModel(RestUtil.MODULE_FINANCIAL_REPORTS, RestUtil.REQUEST_TYPE_LIST);
        Object ObjectList[] = new Object[2];
        String[][] tableRowColumnValues;
        double[] footerValues;
        int rowCount = 0;

        try {
            int row, col;

            StringBuilder rowBranchNamesAndLocationIdSql = new StringBuilder();
            rowBranchNamesAndLocationIdSql
                    .append("SELECT loc.id, loc.hierarchy_id, loc.`name` ")
                    .append("FROM location loc ");
            if(StringUtils.isNotBlank(locationId))
                rowBranchNamesAndLocationIdSql
                        .append("WHERE loc.hierarchy_id LIKE '%>" + locationId + ">%' ")
                        .append("AND loc.hierarchy_id NOT IN ('>" +locationId+">') ");

            rowBranchNamesAndLocationIdSql.append("ORDER BY loc.`name` ");

            // All Branch name and LocationId and LocationHierarchy list
            List rowBranchNamesAndLocationIdList = expenditureRepository.loadsByNativeQuery(rowBranchNamesAndLocationIdSql.toString());

            row=rowBranchNamesAndLocationIdList.size();



           /*
            total number of column for each row
            Here we add 2 manually because we add two column in view manually
            1. Branch
            2. No of School
           */
            col = totalNumberOfColumn+2;


            tableRowColumnValues = new String[row][col];


            for (Object rowBranchLocId : rowBranchNamesAndLocationIdList) { //passing branch name and total column value
                Object[] objsBranchLocId = (Object[]) rowBranchLocId;

                tableRowColumnValues[rowCount] = getColumnValues(
                        objsBranchLocId[2].toString(),
                        col,
                        objsBranchLocId[0].toString(),
                        headsOfAccountUdvId
                );

                rowCount++;
            }

            // Now get the table footer values
            footerValues=getTableFooter(tableRowColumnValues,row,col);

            ObjectList[0] = tableRowColumnValues;
            ObjectList[1] = footerValues;

            response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, ObjectList);
        } catch (Throwable t) {
            response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
        }
        return response;
    }

    @Override
    public ResponseModel deleteById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

   /* @Override
    public ResponseModel getColumnsName(String headsOfAccountUdvId, String year) {
        ResponseModel response = new ResponseModel(RestUtil.COLUMN_NAMES, RestUtil.REQUEST_TYPE_LIST);
        response = getColumnsNameByHeadOfAccount(headsOfAccountUdvId,year);
        return response;
    }
*/
    public String[] getColumnValues(String branchName, int totalNumberOfColumn, String locationId, String headsOfAccountUdvId) {
        int temCount = 0,lastIndex=0;
        Double subTotal = 0.0d, totalExpenditure=0.0d,surplusDeposit=0.0d;
        Object resultAndIndex[]= new Object[2];

        // This col array is row for table
        String[] col = new String[totalNumberOfColumn];

        //insert first column value Branch Name
        col[temCount++] = branchName;

        try {
                StringBuilder getTotalNoOfInstitute = new StringBuilder();
                getTotalNoOfInstitute
                        .append("SELECT DISTINCT COUNT(*) ")
                        .append("FROM institute ins ")
                        .append("WHERE ins.location_hierarchy LIKE '%>").append(locationId).append(">%'");

                List totalNoOfInstitute = expenditureRepository.loadsByNativeQuery(getTotalNoOfInstitute.toString());
                //totalNoOfInstitute.get(0);

                //insert Second column value No to School
                col[temCount++] = totalNoOfInstitute.get(0).toString();



                StringBuilder getAllheadOfAccountIdsSql = new StringBuilder();

                if(StringUtils.isNotBlank(headsOfAccountUdvId)){
                    getAllheadOfAccountIdsSql.append(
                                                    "SELECT udv.id, udv.value FROM udv WHERE udv.category = '" +
                                                    "Heads of Accounts' " +
                                                    "AND udv.id='"+headsOfAccountUdvId+"'");
                }
                else{
                    getAllheadOfAccountIdsSql.append(
                                                    "SELECT udv.id, udv.value FROM udv WHERE udv.category = " +
                                                    "'Heads of Accounts'");
                }
                List udvParentIdRawList = expenditureRepository.loadsByNativeQuery(getAllheadOfAccountIdsSql.toString());

                for (Object udvParentId : udvParentIdRawList) {
                    Object[] headOfAcc = (Object[]) udvParentId;

                    if (headOfAcc[1].toString().equals("Student fee Recovery")) {
                        // before calling getStudentFeeRecoveryColumnsValue add the total Expenditure value to col.
                        col[temCount++] = totalExpenditure.toString();

                       // Call Student Fee Recovery Columns
                        resultAndIndex = getStudentFeeRecoveryColumnsValue(headOfAcc[0].toString(), locationId, col, temCount);

                        col = (String[])resultAndIndex[0];
                        if(StringUtils.isBlank(headsOfAccountUdvId)) {
                            lastIndex = (int) resultAndIndex[1];
                            surplusDeposit = IUtil.toDouble(col[temCount]) - IUtil.toDouble(col[lastIndex]);
                            col[lastIndex++] = surplusDeposit.toString();
                        }
                        //return col;
                    }
                    else {
                        StringBuilder columnValueSql = new StringBuilder();
                        columnValueSql.append("SELECT exp.id, ")
                            .append("udv.`value`, ")
                            .append("SUM(exp.amount) 'Total' ")
                            .append("FROM expenditure exp JOIN location loc ")
                            .append("ON exp.location_id = loc.id JOIN udv udv ")
                            .append("ON udv.id = expenditure_type_udv_id ")
                            .append("AND loc.`name` = \"" + branchName + "\" ")
                            .append("AND udv.parent_id = '" + headOfAcc[0].toString() + "' ")
                            .append("AND exp.location_id = '" + locationId + "'")
                            .append("GROUP BY exp.expenditure_type_udv_id ")
                            .append("ORDER BY udv.`value` ");

                        //================ get number of columns for a 'head of account' =================
                        StringBuilder temSql = new StringBuilder();
                        temSql.append("SELECT udv.id, udv.`value` ")
                                .append("FROM udv udv ")
                                .append("WHERE udv.parent_id = '" + headOfAcc[0].toString() + "' ORDER BY udv.`value`;");
                        List headerList = expenditureRepository.loadsByNativeQuery(temSql.toString());

                        //================ end get number of column for head of account =================
                        List dataList = expenditureRepository.loadsByNativeQuery(columnValueSql.toString());

                        for (Object headerObj : headerList) {
                            for (Object dataObj : dataList) {
                                Object[] objsHeder = (Object[]) headerObj;
                                Object[] objsData = (Object[]) dataObj;

                                if (objsHeder[1].toString().equals(objsData[1].toString())) {
                                    col[temCount] = objsData[2].toString();
                                    if (IUtil.isNumeric(col[temCount])) {
                                        subTotal += Double.parseDouble(col[temCount]);
                                    }
                                    break;
                                }

                            }
                            temCount++;
                        }
                        col[temCount++] = subTotal.toString();
                        totalExpenditure += subTotal;
                        subTotal = 0.0d;
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return col;

    }

    public ResponseModel getColumnsNameByHeadOfAccount(String headsOfAccountUdvId, String year) {
        // Here we generate only table column header
        ResponseModel response = new ResponseModel(RestUtil.COLUMN_NAMES, RestUtil.REQUEST_TYPE_LIST);
        String category;
        StringBuilder sql = new StringBuilder();
        int[] colspanArrayForHeadOfAcccountOfTableHeader = null;
        int spanCount = 0,totalColumn = 0;
        int headOfAccountIdListCount = 0;

        List list;
        UdvModel model;
        List<UdvModel> modelList = new ArrayList<UdvModel>();
        List<UdvModel> headOfAccountModelList = new ArrayList<UdvModel>();
        
        // Here we create object array to store column values and <td> span values
        Object columnAndSpan[] = new Object[2];

        try {

            //First get all head of account ID from udv table by category ===== Start
            List<String> headOfAccountIdList = new ArrayList<>();
            category = "Heads of Accounts";

            if(StringUtils.isNotBlank(headsOfAccountUdvId)){
                sql.append("SELECT udv.id, udv.value ")
                        .append("FROM udv udv ")
                        .append("WHERE udv.category = '" + category + "' ")
                        .append("AND udv.id = '"+ headsOfAccountUdvId+"'");
            }else {
                sql.append("SELECT udv.id, udv.value ")
                        .append("FROM udv udv ")
                        .append("WHERE udv.category = '" + category + "'");
            }

            headOfAccountIdList = expenditureRepository.loadsByNativeQuery(sql.toString());
            colspanArrayForHeadOfAcccountOfTableHeader = new int[headOfAccountIdList.size()];
            sql.setLength(0);

            //First get all head of account ID from udv table by category ===== Start
            for (Object object : headOfAccountIdList) {
                Object[] headOfAccount = (Object[]) object;
                model = new UdvModel();
                model.setId(IUtil.toString(headOfAccount[0]));
                model.setValue(IUtil.toString(headOfAccount[1]));
                headOfAccountModelList.add(model);
            }
            //First get all head of account ID from udv table by category ===== End


            //Enter the Branch column as header manually === Start
            model = new UdvModel();
            model.setValue("Branch");
            modelList.add(model);
            //Enter the Branch column as header manually ==== End

            //Enter the No of School column as header manually === Start
            model = new UdvModel();
            model.setValue("No of Institute");
            modelList.add(model);
            //Enter the No of School column as header manually ==== End

            // Get all Columns Name by headOfAccountId  and category ================================= Start
            for (UdvModel headOfAccountId : headOfAccountModelList) {

                // Add Total Expenditure column manually before staring adding columns of Student fee Recovery head ========== Start
                if (StringUtils.equals(headOfAccountId.getValue(), "Student fee Recovery")
                        && StringUtils.isBlank(headsOfAccountUdvId)) {
                    model = new UdvModel();
                    model.setValue("Total Expenditure");
                    modelList.add(model);
                    totalColumn++;
                }
                //  Add Total Expenditure column manually before staring adding columns of Student fee Recovery head ========== End

                //get all columns under specific head of account id from udv
                sql.append("SELECT udv.id, udv.`value` ")
                        .append("FROM udv udv ")
                        .append("WHERE udv.parent_id = '" + headOfAccountId.getId() + "' ORDER BY udv.`value`;");

                list = expenditureRepository.loadsByNativeQuery(sql.toString());
                sql.setLength(0);
                for (Object object : list) {
                    Object[] objs = (Object[]) object;
                    model = new UdvModel();
                    model.setId(IUtil.toString(objs[0]));
                    model.setValue(IUtil.toString(objs[1]));
                    modelList.add(model);
                    spanCount++;
                }
                //Add subtotal Column manually === Start
                model = new UdvModel();
                model.setValue("Sub Total");
                modelList.add(model);
                spanCount++;
                //Add subtotal Column manually === End

                // <td> span count for each head of account
                colspanArrayForHeadOfAcccountOfTableHeader[headOfAccountIdListCount] = spanCount;
                headOfAccountIdListCount++;
                totalColumn += spanCount;
                spanCount = 0;
            }
            // Get all Columns Name by headOfAccountId  and category ================================= End

            if(StringUtils.isBlank(headsOfAccountUdvId)) {
                // Add Total Surplus/Deposit column manually ========== Start
                model = new UdvModel();
                model.setValue("Surplus / Deposit");
                modelList.add(model);
                totalColumn++;
                // Add Total Expenditure column manually ========== End
            }

            columnAndSpan[0] = colspanArrayForHeadOfAcccountOfTableHeader;
            columnAndSpan[1] = modelList;
            response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, columnAndSpan);
            response.setTotal(String.valueOf(totalColumn));

        } catch (Exception e) {
            response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
        }
        return response;
    }

    public double[] getTableFooter(String[][] tableRowColumnValues, int row, int col){
        /*
            total number of column for each row
            Here we minus 2 manually because we did not need branch name
            1. Branch
           */
        double footer[] = new double[col-1];
        int temCount=0;
        Double temSum = 0.0d;
        String value;

        for(int i=0; i<row; i++){
            for(int j=1; j<col; j++){
                if(StringUtils.isNotBlank(tableRowColumnValues[i][j]))
                    if(IUtil.isNumeric(tableRowColumnValues[i][j])){
                        try {
                            value =tableRowColumnValues[i][j];
                            footer[j-1]+= Double.parseDouble(value);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            }
        }

        return footer;
    }

    public  Object[] getStudentFeeRecoveryColumnsValue(String headAccId, String locationId, String[] col, int temCount ){
        int temRow=0;
        double subTotal=0.0d;
        double [] stdFeeRecValue=null;
        double [] resultForOneLocation;
        Object [] resultAndIndex= new Object[2];
        //================ get number of columns for a 'head of account' =================
        StringBuilder haderListSql = new StringBuilder();
        haderListSql.append("SELECT udv.id, udv.`value` ")
                .append("FROM udv udv ")
                .append("WHERE udv.parent_id = '" + headAccId + "' ORDER BY udv.`value`;");
        //================ end get number of column for head of account =================

        try {
            List headerList = expenditureRepository.loadsByNativeQuery(haderListSql.toString());

            stdFeeRecValue = new double[headerList.size()];

            StringBuilder dataSql = new StringBuilder();
            StringBuilder getAllInstitutesByLocationIdSql = new StringBuilder();
            getAllInstitutesByLocationIdSql.append("SELECT ins.id as Ins_ID,  ins.location_id , loc.`name` as Loation, ins.`name` as Institute ")
                    .append("FROM institute ins JOIN location loc ON (ins.location_id = loc.id) ")
                    .append("AND loc.id = '" + locationId + "' ORDER BY ins.`name`");

            List instituteList = expenditureRepository.loadsByNativeQuery(getAllInstitutesByLocationIdSql.toString());

            for (Object instituteId : instituteList) {

                Object[] objs = (Object[]) instituteId;

                dataSql.append("SELECT sum(trn.deposited) AS 'Deposited', ")
                        .append("ud.`value` ")
                        .append("FROM transaction_history trn ")
                        .append("JOIN institute ins ON (trn.institute_id = ins.id) ")
                        .append("JOIN udv ud ON (trn.fee_type_udv_id=ud.id) ")
                        .append("WHERE ins.id = '" + objs[0].toString() + "' ")
                        .append("GROUP BY trn.fee_type_udv_id ")
                        .append("ORDER BY ud.`value ");

                List dataList = expenditureRepository.loadsByNativeQuery(dataSql.toString());
                dataSql.setLength(0);

                if(dataList.size()>0) {
                    temRow=0;
                    for (Object headerObj : headerList) {
                        for (Object dataObj : dataList) {
                            Object[] objsHeder = (Object[]) headerObj;
                            Object[] objsData = (Object[]) dataObj;

                            if (objsHeder[1].toString().equals(objsData[1].toString())) {
                                //col[temCount] = objsData[2].toString();
                                if (StringUtils.isNotBlank(objsData[0].toString())) {
                                    stdFeeRecValue[temRow] += Double.parseDouble(objsData[0].toString());
                                    break;
                                }
                            }
                        }
                        temRow++;
                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
        }

        for (double value : stdFeeRecValue) {
            if (value > 0) {
                col[temCount++] = IUtil.toString(value);
                subTotal += value;
            } else
                temCount++;
        }
        col[temCount++] = IUtil.toString(subTotal);

        resultAndIndex[0] = col;
        resultAndIndex[1] = temCount;
        return  resultAndIndex;

    }
}
