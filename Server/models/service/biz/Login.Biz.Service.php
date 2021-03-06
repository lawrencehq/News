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
		$responce;
		if ($result != null) {
			$responce = array(
						'status' => true,
						'data' => $result[0]);
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