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

	public function add($email, $pass, $username) {
		$sql = "insert into user values (default,'".$email."','".$pass."','".$username."',default)";
		$result = $this->connection->query($sql);
		return $result;
	}

	public function getUserById($userId) {
		$sql = "select * from user where userId=".$userId;
		$result = $this->connection->query($sql);
		return $result;
	}

	public function checkPass($email, $pass) {
		$sql = "select * from user where email='".$email."' and password='".$pass."'";
		$result = $this->connection->query($sql);
		return $result;
	}

}
?>