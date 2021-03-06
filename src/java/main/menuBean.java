/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.ArrayList;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author tw
 */
@Named(value = "menuBean")
@SessionScoped
public class menuBean implements Serializable {

    public static String pathCont = "/home.xhtml";
    public static String pathLeft = "/leftMenu/employee_leftMenu.xhtml";
    boolean isShowMobileMenu = false;

    public menuBean() {

    }

    public void keepSessionAlive() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        request.getSession();
        System.out.println(request.getSession().getLastAccessedTime());
        System.out.println(request.getSession().getCreationTime());
        System.out.println(">>> Session is alive... ");
        try {
            RequestContext.getCurrentInstance().update("form");
            int maxInactiveInterval = request.getSession().getMaxInactiveInterval();
            System.out.println(maxInactiveInterval);
            request.getSession().setMaxInactiveInterval(maxInactiveInterval);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            System.out.println(request.getSession().getLastAccessedTime());
            System.out.println(request.getSession().getCreationTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        pathCont = "/home.xhtml";
        pathLeft = "/leftMenu/employee_leftMenu.xhtml";
    }

    public void showMobileMenu(boolean isShow) {
        isShowMobileMenu = isShow;
    }

    public String redirectWelcome() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signinBean sbean = (signinBean) elContext.getELResolver().getValue(elContext, null, "signinBean");
        System.out.println("Redirect Welcome!");
        return sbean.logout();
    }

    public static void redirectHome() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signinBean sbean = (signinBean) elContext.getELResolver().getValue(elContext, null, "signinBean");
        switch (sbean.em.role) {
            case "doctor": {
                treatmentBean bean = (treatmentBean) elContext.getELResolver().getValue(elContext, null, "treatmentBean");
                bean.reset();
                bean.findDynaPatient();
                break;
            }
            case "nurse": {
                triageBean bean = (triageBean) elContext.getELResolver().getValue(elContext, null, "triageBean");
                bean.reset();
                bean.findDynaPatient();
                break;
            }
        }
        pathCont = "/home.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void detailInsurance() {
        pathCont = "/insurance/insurance_detail.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void writeInsurance() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        insuranceBean bean = (insuranceBean) elContext.getELResolver().getValue(elContext, null, "insuranceBean");
        bean.reset();
        pathCont = "/insurance/insurance_write.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void viewInsurance() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        insuranceBean bean = (insuranceBean) elContext.getELResolver().getValue(elContext, null, "insuranceBean");
        bean.reset();
        pathCont = "/insurance/insurance_view.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // User Information
    public static void viewPatientDemographics() {
        pathCont = "/userInfo/view_patient.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void viewEmployeeDemographics() {
        pathCont = "/userInfo/view_employee.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void editEmployee() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signupBean bean = (signupBean) elContext.getELResolver().getValue(elContext, null, "signupBean");
        bean.reset();
        bean.setUpdateValue();
        pathCont = "/admin/create_employee.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void findEmployee() {
        pathCont = "/userInfo/find_employee.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Patient Information
    public static void patientMedicalHistoryWithReset() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        patientBean bean = (patientBean) elContext.getELResolver().getValue(elContext, null, "patientBean");
        bean.reset();
        pathCont = "/patientInfo/medical_history_patient.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void patientMedicalHistory() {
        pathCont = "/patientInfo/medical_history_patient.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void viewMHDocument() {
        pathCont = "/patientInfo/viewFileContent.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Patient Check-In
    public static void newPatient() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signupBean bean = (signupBean) elContext.getELResolver().getValue(elContext, null, "signupBean");
        bean.reset();
        pathCont = "/checkin/create_patient.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void checkinPatient() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        patientBean bean = (patientBean) elContext.getELResolver().getValue(elContext, null, "patientBean");
        bean.reset();
        pathCont = "/checkin/checkin_patient.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Nursing Station
    public static void patientStatus() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        patientStatusBean bean = (patientStatusBean) elContext.getELResolver().getValue(elContext, null, "patientStatusBean");
        bean.reset();
        bean.findDynaPatient();
        pathCont = "/nursing_station/patient_status.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void triage() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        triageBean bean = (triageBean) elContext.getELResolver().getValue(elContext, null, "triageBean");
        bean.reset();
        pathCont = "/nursing_station/triage.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Treatment
    public static void diagnosis() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        treatmentBean bean = (treatmentBean) elContext.getELResolver().getValue(elContext, null, "treatmentBean");
        bean.reset();
        bean.allDate = true;
        bean.findDynaPatient();
        pathCont = "/treatment/diagnosis.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void writeVisitSummary() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        treatmentBean bean = (treatmentBean) elContext.getELResolver().getValue(elContext, null, "treatmentBean");
        bean.reset();
        pathCont = "/treatment/write_visit_summary.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Prescription
    public static void fulFillPrescription() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        prescriptionBean bean = (prescriptionBean) elContext.getELResolver().getValue(elContext, null, "prescriptionBean");
        bean.reset();
        bean.findPrescription();
        pathCont = "/prescription/fulfill_prescription.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Order
    public static void viewLabOrder() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        orderBean bean = (orderBean) elContext.getELResolver().getValue(elContext, null, "orderBean");
        bean.reset();
        bean.findLabOrder();
        pathCont = "/order/view_lab_order.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void viewImagingOrder() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        orderBean bean = (orderBean) elContext.getELResolver().getValue(elContext, null, "orderBean");
        bean.reset();
        bean.findImagingOrder();
        pathCont = "/order/view_imaging_order.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Payment
    public static void viewPatientBill() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        paymentBean bean = (paymentBean) elContext.getELResolver().getValue(elContext, null, "paymentBean");
        bean.reset();
        pathCont = "/payment/view_patient_bill.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    // Admin
    public static void newEmployee() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signupBean bean = (signupBean) elContext.getELResolver().getValue(elContext, null, "signupBean");
        bean.reset();
        pathCont = "/admin/create_employee.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void newLocation() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signupBean bean = (signupBean) elContext.getELResolver().getValue(elContext, null, "signupBean");
        bean.reset();
        pathCont = "/admin/create_location.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void manageLocation() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signupBean bean = (signupBean) elContext.getELResolver().getValue(elContext, null, "signupBean");
        bean.reset();
        bean.findLocationList();
        pathCont = "/admin/manage_location.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    //Inventory
    public static void addClinicalInventory() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        inventoryBean bean = (inventoryBean) elContext.getELResolver().getValue(elContext, null, "inventoryBean");
        bean.reset();
        pathCont = "/inventory/add_clinical_inventory.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void manageClinicalInventory() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        inventoryBean bean = (inventoryBean) elContext.getELResolver().getValue(elContext, null, "inventoryBean");
        bean.reset();
        bean.searchAllManageInfo();
        pathCont = "/inventory/manage_clinical_inventory.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public static void manageSupplyInventory() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        inventorySupplyBean bean = (inventorySupplyBean) elContext.getELResolver().getValue(elContext, null, "inventorySupplyBean");
        bean.reset();
        pathCont = "/inventory/manage_supply_inventory.xhtml";
        RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
    }

    public String getPathCont() {
        return pathCont;
    }

    public void setPathCont(String pathCont) {
        this.pathCont = pathCont;
    }

    public String getPathLeft() {
        return pathLeft;
    }

    public void setPathLeft(String pathLeft) {
        menuBean.pathLeft = pathLeft;
    }

    public boolean isIsShowMobileMenu() {
        return isShowMobileMenu;
    }

    public void setIsShowMobileMenu(boolean isShowMobileMenu) {
        this.isShowMobileMenu = isShowMobileMenu;
    }

    public boolean isRendered(String strs) {

        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        signinBean bean = (signinBean) elContext.getELResolver().getValue(elContext, null, "signinBean");

        if (!"superadmin".equals(strs) && ("admin".equals(bean.em.role.toLowerCase()) || "superadmin".equals(bean.em.role.toLowerCase()))) {
            return true;
        }

        if (bean.em.role == null || "".equals(bean.em.role)) {
            return false;
        }

        String role = bean.em.role.toLowerCase();
        for (String s : strs.split("/")) {
            if (s.toLowerCase().equals(role)) {
                return true;
            }
        }

        return false;
    }

}
