<?php
/**
 * @file Login.Action.php
 * @author hq
 * @date 2015/11/18
 *
 **/
class Login_Action {
	private $loginBiz;

	public function execute() {
		$this->init();
		$params = $this->getParams();
		return $this->loginBiz->execute($params);
	}

	private function getParams() {
		$parameters = array(
					'email' => isset($_POST['email']) ? $_POST['email'] : 'Null',
					'pass' => isset($_POST['pass']) ? $_POST['pass'] : 'Null'
					);
		return $parameters;
	}

	private function init() {
		$this->loginBiz = new Login_Biz_Service();
	}
}
?>
