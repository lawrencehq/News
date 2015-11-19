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

	public function getUserById($userId) {
		return $this->userDao->getUserById($userId);
	}

	public function checkPassword($email, $pass) {
		return $this->userDao->checkPass($email, $pass);
	}

	public function addUser($email, $pass, $username) {
		if ($email == null || $pass == null || $username == null) {
			return 0;
		}
		return $this->userDao->add($email, $pass, $username);
	}
}
?>