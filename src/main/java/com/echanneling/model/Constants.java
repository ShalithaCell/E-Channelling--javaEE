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
    /** Constant for my sql datetime format **/
    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /** Constant for project account verification url **/
    public static final String VERIFICATION_URL = "projectAccountVerificationURL";
    /** Constant for project account verification url **/
    public static final String VERIFICATION_CODE = "verification";

    /** Constant for verificationCodeSize **/
    public static final int VERIFICATION_CODE_SIZE = 15;

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
    public static final String HTTP500PAGE = "/WEB-INF/view/HTTP500.jsp";

    public static final String EXEPTION_MAIL = "WEB-INF/EmailTempletes/ErrorInformTemplete.html";
    public static final String VERIFICATION_MAIL = "WEB-INF/EmailTempletes/VerificationMail.html";
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

}
