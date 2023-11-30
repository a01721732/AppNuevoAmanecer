<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Alumno;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use View;
use Redirect;

class AlumnoController extends Controller
{
    public function index($iIdAlumno=null){
        if(session()->get('key') != null){
            try{ 

                if($iIdAlumno == null){
                    $data = DB::select("call procAlumno(?)", [$iIdAlumno] );
    
                    return View::make('alumno.index')->with('alumnos', $data);
                }
                else{
                    $data = DB::select("call procAlumno(NULL)");
                    $alumno = DB::select("call procAlumno(?)", [$iIdAlumno]);
                   return View::make('Alumno.index')->with('alumnos', $data)->with('alumno', $alumno[0]);
                }
                
            }
            catch (Throwable $e){
                return '';
            }
        }
        else{
            return Redirect::to('/');
        }
    }

    public function store(Request $request){
        
        try{ 

            $sNombreAlumno = $request->input('sNombreAlumno');
            $sApellidoAlumno = $request->input('sApellidoAlumno');
            $iEdad = $request->input('iEdad');
            $iNivelCognitivo = $request->input('iNivelCognitivo');
            $bActivo = $request->input('bActivo');

            DB::statement("call procAlumnoABC(0, '".$sNombreAlumno."', '".$sApellidoAlumno."', ".$iEdad.", ".$iNivelCognitivo.", ".$bActivo.", 0)");
            return Redirect::to('Alumno');
        }
        catch (Throwable $e){
            return '';
        }
    }

    public function update(Request $request){
        
        try{ 
            $iIdAlumno = $request->input('iIdAlumno');

            $sNombreAlumno = $request->input('sNombreAlumno');
            $sApellidoAlumno = $request->input('sApellidoAlumno');
            $iEdad = $request->input('iEdad');
            $iNivelCognitivo = $request->input('iNivelCognitivo');
            $bActivo = $request->input('bActivo');


            DB::statement("call procAlumnoABC('".$iIdAlumno."', '".$sNombreAlumno."', '".$sApellidoAlumno."', ".$iEdad.",
                                             ".$iNivelCognitivo.", ".$bActivo.", 0)");

            return Redirect::to('Alumno');
            
        }
        catch (Throwable $e){
            return '';
        }
    }

    public function delete($iIdAlumno){
        DB::statement("call procAlumnoABC(".$iIdAlumno.", '', '', 0, 0, 0, 1)");
        return Redirect::to('Alumno');
    }

    public function getAlumnoData($iIdPsicologo){
        try{ 
            $data = DB::select('call procAlumnoPsicologo(?)', [$iIdPsicologo]);
            return $data;
        }
        catch (Exception $e){
            return '';
        }

    }

    public function updateNivelCognitivo(Request $request){
        try{ 
            $iIdAlumno = $request->input('iIdAlumno');
            $iNivel = $request->input('iNivel');

            $data = DB::select('call procAlumnoUpdateNivel(?, ?)', [$iIdAlumno, $iNivel]);
            return $data;
        }
        catch (Exception $e){
            return '';
        }

    }
}
