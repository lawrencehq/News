<?php
/**
 * @file SignUp.Biz.Service.php
 * @author hq
 * @date 2015/11/19
 *
 **/
class SignUp_Biz_Service {
	private $userData;

	public function execute($params) {
		$this->init();
		$result = $this->userData->addUser($params['email'], $params['pass'], $params['username']);
		$responce;
		if ($result != 0) {
			$user = $this->userData->getUserById($result);
			$responce = array(
						'status' => true,
						'data' => $user[0]);
		} else {
			$responce = array(
						'status' => false,
						'data' => null);
		}
		return $responce;
	}

	private function init() {
		$this->userData = new User_Data_Service();
	}
}
?>