package com.echanneling.model;

/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */

public class Constants {

    /** =============================== Default Constants ======================================== */

    /** Constant for config.properties **/
    public static final String PROPERTY_FILE = "config.properties";

    /** Constant for mail sender **/
    public static final String MAIL_SENDER = "mailSender";

    /** Constant for mail sender password **/
    public static final String MAIL_SENDER_PASS = "mailSenderPassword";

    /** Constant for String.Empty **/
    public static final String STRING_EMPTY = "";

    /** Constant for driver name key of MySQL database */
    public static final String DRIVER_NAME = "driverName";

    /** Constant for connection string */
    public static final String CONNECTION_STRING = "connectionString";

    /** Constant for secret and salt */
    public static final String SECRET_KEY = "secretKey";

    public static final String SALT = "salt";

    public static final String XML_ROOT_NODE_QURIES = "queries";

    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String SUCCESS = "success";



    /** =============================== Default Keys ======================================== */

    /** Constant for developer mail address **/
    public static final String DEVELOPER_EMAIL = "developerMailAddress";

    /** Constant for simple date time format **/
    public static final String SIMPLE_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    /** Constant for simple date time format for web input**/
    public static final String SIMPLE_DATE_FORMAT_WEB_INPUT = "dd/MM/yyyy";
    /** Constant for simple date time format for web input 2**/
    public static final String SIMPLE_DATE_FORMAT_WEB_INPUT2 = "yyyy-MM-dd";
    /** Constant for my sql datetime format **/
    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /** Constant for project account verification url **/
    public static final String VERIFICATION_URL = "projectAccountVerificationURL";
    /** Constant for project account verification url **/
    public static final String VERIFICATION_CODE = "verification";
    /** Constant for project URL **/
    public static final String PROJECTDOMAIN = "projectURL";
    /** Constant for project Forgot Password address **/
    public static final String FORGETPASSWORD_CODE = "projectAccountResetURL";

    /** Constant for project account verification url **/
    public static final String ACCOUNT_RESET_CODE = "resetToken";


    /** Constant for verificationCodeSize **/
    public static final int VERIFICATION_CODE_SIZE = 15;
    /** Constant for PasswordResetTocken **/
    public static final int PASSWORD_RESET_TOKEN_LINK = 10;

    /** Constant for Stand User Role Code **/
    public static final int STANDUSER_ROLE_CODE = 3;

    /** =============================== Page identifying keys ======================================== */
    public static final String HOME_PAGE_KEY = "home";
    public static final String LOGIN_PAGE_KEY = "login";
    public static final String ERROR_PAGE_KEY = "Error";


    /** =============================== Default Routing ======================================== */
    public static final String HOMEPAGE = "/WEB-INF/view/index.jsp";
    public static final String LOGINPAGE = "/WEB-INF/view/account/login.jsp";
    public static final String VERIFICATIONPAGE = "/WEB-INF/view/account/AccountVerification.jsp";
    public static final String ADMINPANELPAGE = "/WEB-INF/view/system/AdminPanel.jsp";
    public static final String PAYMENTPAGE = "/WEB-INF/view/system/payment.jsp";
    public static final String HTTP500PAGE = "/WEB-INF/view/HTTP500.jsp";
    public static final String USERACCOUNTPAGE = "/WEB-INF/view/account/UserAccount.jsp";
    public static final String PASSWORDRESETPAGE = "/WEB-INF/view/account/PasswordReset.jsp";

    public static final String EXEPTION_MAIL = "WEB-INF/EmailTempletes/ErrorInformTemplete.html";
    public static final String VERIFICATION_MAIL = "WEB-INF/EmailTempletes/VerificationMail.html";
    public static final String FORGETPASSWORD_MAIL = "WEB-INF/EmailTempletes/PAsswordResetEmailTemplate.html";
    public static final String CONFIG_XML = "WEB-INF/SystemInfo.xml";


    /** =============================== Default SQL column indexes ======================================== */
    /** Constant for Column index one */
    public static final int COLUMN_INDEX_ONE = 1;

    /** Constant for Column index two */
    public static final int COLUMN_INDEX_TWO = 2;

    /** Constant for Column index three */
    public static final int COLUMN_INDEX_THREE = 3;

    /** Constant for Column index four */
    public static final int COLUMN_INDEX_FOUR = 4;

    /** Constant for Column index five */
    public static final int COLUMN_INDEX_FIVE = 5;

    /** Constant for Column index six */
    public static final int COLUMN_INDEX_SIX = 6;

    /** Constant for Column index seven */
    public static final int COLUMN_INDEX_SEVEN = 7;

    /** Constant for Column index eight */
    public static final int COLUMN_INDEX_EIGHT = 8;

    /** =============================== Default SQL Query Ids ======================================== */
    public static final String SQL_CHECK_USER_EMAIL_DUPLICATES = "checkEmailDuplicate";
    public static final String SQL_REGISTER_TEMP_USER = "registerTempUser";
    public static final String SQL_GET_TEMP_USER = "getTempUserByVerificationCode";
    public static final String SQL_GET_TEMP_USER_DETAILS = "getTempUserDetails";
    public static final String SQL_REGISTER_USER = "registerUserSP";
    public static final String SQL_USER_LOGIN = "checkLogin";
    public static final String SQL_GET_REGISTED_USER = "GetRegisterUserDetails";
    public static final String SQL_UPDATE_USER = "UpdateUserDetails";
    public static final String SQL_GET_USER_BY_EMAIL = "GetUserByEmailSP";
    public static final String SQL_SAVE_RESET_TOKEN = "SavePasswordResetToken";
    public static final String SQL_VALIDATE_TOKEN = "ValidatePasswordResetToken";
    public static final String SQL_PASSWORD_CHANGE = "PasswordChangeSP";
    public static final String SQL_GET_ALL_HOSPITALS = "getAllHospitals";
    public static final String SQL_ADD_NEW_HOSPITAL = "AddNewHospital";
    public static final String SQL_GET_HOSPITAL_BY_ID = "getHospitalByID";
    public static final String SQL_UPDATE_HOSPITAL = "UpdateHospital";
    public static final String SQL_DELETE_HOSPITAL = "RemoveHospital";
    public static final String SQL_GET_ALL_DOCTORS = "getAllDoctors";
    public static final String SQL_ADD_NEW_DOCTOR = "AddNewDoctor";
    public static final String SQL_GET_DOCTOR_BY_ID = "GetDoctorByID";
    public static final String SQL_UPDATE_DOCTOR = "updateDoctor";
    public static final String SQL_REMOVE_DOCTOR = "DeleteDoctor";
    public static final String SQL_ADD_APPOINTMENT = "addAppointment";
    public static final String SQL_ADD_PAYMENT = "addPayment";

    /** =============================== Session Variables ======================================== */

    public static final String SESSION_USER_ID = "UserID";
    public static final String SESSION_RoleID = "FK_RoleID";
    public static final String SESSION_FirstName = "FirstName";
    public static final String SESSION_LastName = "LastName";
    public static final String SESSION_Email = "Email";
    public static final String SESSION_GenderID = "FK_GenderID";
    public static final String SESSION_DOB = "DOB";
    public static final String SESSION_CONTACT = "Contact";

}
