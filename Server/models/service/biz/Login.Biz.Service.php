<?php
/**
 * @file Login.Biz.Service.php
 * @author hq
 * @date 2015/11/18
 *
 **/
class Login_Biz_Service {
	private $userData;

	public function execute($params) {
		$this->init();
		$result = $this->userData->checkPassword($params['email'], $params['pass']);
		if ($result != null) {
			return true;
		} else {
			return false;
		}
	}

	private function init() {
		$this->userData = new User_Data_Service();
	}
}
?>