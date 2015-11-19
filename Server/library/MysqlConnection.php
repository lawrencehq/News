<?php
/**
 * @file MysqlConnection.php
 * @author hq
 * @date 2015/11/18
 *
 **/
class MysqlConnection {
	private $servername = "localhost";
	private $username = "root";
	private $password = "123456";
	private $dbName = "news";

	public function query($sql) {
		//connect to the database
		$conn = mysqli_connect($this->servername, $this->username, $this->password, $this->dbName);

		//get results
		$result;
		$array = explode(" ", $sql);
		$type = $array[0];
		switch ($type) {
			case 'insert':
				$result = $this->insert($sql, $conn);
				break;
			case 'select':
				$result = $this->select($sql, $conn);
				break;
			case 'update':
				$result = $this->update($sql, $conn);
				break;
			case 'delete':
				$result = $this->delete($sql, $conn);
				break;
			
			default:
				$result = "Invalid sql query.";
				break;
		}
		
		//disconnect
		mysqli_close($conn);

		return $result;
	}

	private function insert($sql, $conn) {
		$result;
		if (mysqli_query($conn, $sql)) {
			$result = mysqli_insert_id($conn);
		} else {
			$result = 0;
		}
		return $result;
	}

	private function select($sql, $conn) {
		$result = null;
		$queryResult = mysqli_query($conn, $sql);
		if (mysqli_num_rows($queryResult) > 0) {
			$result = array();
			while($row = mysqli_fetch_assoc($queryResult)) {
				array_push($result, $row);
			}
		}
		return $result;
	}

	private function update($sql, $conn) {
		$result;
		if (mysqli_query($conn, $sql)) {
			$result = true;
		} else {
			$result = false;
		}
		return $result;
	}

	private function delete($sql, $conn) {
		$result;
		if (mysqli_query($conn, $sql)) {
			$result = true;
		} else {
			$result = false;
		}
		return $result;
	}
}
?>