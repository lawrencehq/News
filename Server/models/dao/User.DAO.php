<?php
/**
 * @file User.DAO.php
 * @author hq
 * @date 2015/11/18
 *
 **/
class User_DAO extends MysqlConnection {
	private $connection;

	public function __construct() {
		$this->connection = new MysqlConnection();
	}

	public function getUserId($userId) {
		$sql = "select * from User where userId=".$userId;
		$result = query($sql);
		return $result;
	}

	public function checkPass($email, $pass) {
		$sql = "select * from User where email='".$email."' and password='".$pass."'";
		$result = $this->connection->query($sql);
		return $result;
	}

}
?>