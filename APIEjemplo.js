const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const bodyParser = require('body-parser');

const app = express();
const port = 3000;

app.use(bodyParser.json());

// Conexion a base de datos
const db = new sqlite3.Database('./NuevoAmanecer.db', (err) => {
  if (err) {
    console.error(err.message);
  }
  console.log('Conectado a base de datos de Nuevo Amanecer.');
});

//Ejemplos de Endpoints

// GET: Sacar todos los estudiantes
app.get('/alumnos', (req, res) => {
    const sql = 'SELECT * FROM Alumno';
    db.all(sql, [], (err, rows) => {
      if (err) {
        throw err;
      }
      res.json(rows);
    });
  });
  
  // GET: Sacar a a un estudiante por ID
app.get('/alumnos/:id', (req, res) => {
    const sql = `SELECT * FROM Alumno WHERE ID = ?`;
    const params = [req.params.id];
    db.get(sql, params, (err, row) => {
      if (err) {
        res.status(400).json({"error": err.message});
        return;
      }
      res.json({
        "message": "success",
        "data": row
      });
    });
  });

// POST: Agregar nuevo estudiante
app.post('/alumnos', (req, res) => {
    const { Nombre, Apellido, Edad } = req.body; 
    const sql = `INSERT INTO Alumno (Nombre, Apellido, Edad) VALUES (?, ?, ?)`;
    const params = [Nombre, Apellido, Edad];
    db.run(sql, params, function(err) {
      if (err) {
        res.status(400).json({ "error": err.message });
        return;
      }
      res.json({
        "message": "success",
        "data": req.body,
        "id": this.lastID // ID of the last inserted row
      });
    });
  });

// DELETE: Borrar a un estudiante por ID
app.delete('/alumnos/:id', (req, res) => {
    const sql = `DELETE FROM Alumno WHERE ID = ?`;
    const params = [req.params.id];
    db.run(sql, params, function(err) {
      if (err) {
        res.status(400).json({ "error": res.message });
        return;
      }
      res.json({
        "message": "deleted",
        "changes": this.changes // Number of rows deleted
      });
    });
  });

  // Comenzar servidor
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
  });
