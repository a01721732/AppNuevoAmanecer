<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Alumno;
use Illuminate\Support\Facades\DB;

class AlumnoController extends Controller
{
    public function getAlumnoData($iIdAlumno){
        $data = DB::select('call procAlumno('.$iIdAlumno.')');
        return $data;
    }
}
