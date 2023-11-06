<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    use HasFactory;
    protected $table = 'talumno';
    protected $fillable = ['iIdAlumno', 'sNombre', 'sApellido', 'iEdad', 'iIdNIvelCognitivo', 'bActivo'];
}
