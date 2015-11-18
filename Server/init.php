<?php
/**
 * @file init.php
 * @author hq
 * @date 2015/11/18
 *
 **/

error_reporting(0);
//ob_start();
header('Content-Type: text/html; charset=UTF-8');

define('MOBILE_ROOT', dirname(__FILE__));	//__FILE__ current path
require_once('Controller.php');

require_once(MOBILE_ROOT.'/library/MysqlConnection.php');

require_once(MOBILE_ROOT.'/actions/Login.Action.php');

require_once(MOBILE_ROOT.'/models/service/biz/Login.Biz.Service.php');

require_once(MOBILE_ROOT.'/models/service/data/User.Data.Service.php');

require_once(MOBILE_ROOT.'/models/dao/User.DAO.php');

doStripslashes();

function doStripslashes() {
	if (get_magic_quotes_gpc()) {
		$_GET = stripslashesDeep($_GET);
		$_POST = stripslashesDeep($_POST);
		$_COOKIE = stripslashesDeep($_COOKIE);
		$_REQUEST = stripslashesDeep($_REQUEST);
	}
}

?>