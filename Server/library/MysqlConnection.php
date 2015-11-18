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

	public function query($sql) {
		//connect to the database
		$conn = mysql_connect($this->servername, $this->username, $this->password);

		//get results
		mysql_select_db("news", $conn);
		$queryResult = mysql_query($sql);
		$result = array();
		$i=0;
		while($row = mysql_fetch_array($queryResult)) {
			$result[$i] = $row;
			$i++;
		}


		//release the resource
		mysql_free_result($queryResult);
		//disconnect
		mysql_close($conn);

		return $result;
	}
}
?>