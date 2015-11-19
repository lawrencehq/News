<?php
/**
 * @file SignUp.Action.php
 * @author hq
 * @date 2015/11/19
 *
 **/
class SignUp_Action {
	private $signUpBiz;

	public function execute() {
		$this->init();
		$params = $this->getParams();
		return $this->signUpBiz->execute($params);
	}

	private function getParams() {
		$parameters = array(
					'email' => isset($_POST['email']) ? $_POST['email'] : null,
					'pass' => isset($_POST['pass']) ? $_POST['pass'] : null,
					'username' => isset($_POST['username']) ? $_POST['username'] : null
					);
		return $parameters;
	}

	private function init() {
		$this->signUpBiz = new SignUp_Biz_Service();
	}
}
?>
