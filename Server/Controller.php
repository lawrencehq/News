<?php
/**
 * @file Controller.php
 * @author hq
 * @date 2015/11/18
 *
 **/
class Controller {
	static $instance;

	public $Actions = array(
			'login' => 'Login_Action',
			'signup' => 'SignUp_Action'
		);
	
	public function __construct() {
	}
	
	public function run() {
		$params = $this->params();
		$actionName = $params['action'];
		if (isset($this->Actions[$actionName])) {
			$Action = $this->Actions[$actionName];
			$action = new $Action();
			echo json_encode($action->execute());
		} else {
			echo json_encode("Action not set");
		}
		
	}

	private function params() {
		$action = isset($_POST['action']) ? $_POST['action'] : 'Null'; 
		$params = array(
			'action' => $action
			);
		return $params;
	}

	public static function getInstance() {
		if (self::$instance == null) {
			self::$instance = new Controller();
		}
		return self::$instance;
	}

}
?>
