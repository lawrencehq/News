<?php
/**
 * @file User.Data.Service.php
 * @author hq
 * @date 2015/11/18
 *
 **/

class User_Data_Service {
	private $userDao;

	public function __construct() {
		$this->userDao = new User_DAO();
	}

	public function checkPassword($email, $pass) {
		return $this->userDao->checkPass($email, $pass);
	}
}
?>