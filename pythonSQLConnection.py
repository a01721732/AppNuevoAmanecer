import mysql.connector
#pip install mysql-connector-python

db = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="sqlPassTecIoT506",
    auth_plugin='mysql_native_password'
)

mycursor = db.cursor()

mycursor.execute("CREATE DATABASE IF NOT EXISTS NuevoAmanecer")