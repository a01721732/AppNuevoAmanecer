<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Psicologo;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\RedirectResponse;

use View;
use Redirect;
use Log;

class PsicologoController extends Controller
{
    protected $model; 

    public function __construct()
    {
        $this->model = new Psicologo();
        $this->middleware('web');
    }
    
    public function index($iIdPsicologo=null){
       
        if(session()->get('key') != null){
            try{ 
                if($iIdPsicologo == null){
                    $data = DB::select("call procPsicologo(?)", [$iIdPsicologo] );
                    return View::make('psicologo.index')->with('psicologos', $data);
                }

                if(session()->get('openAlumnos') == true){
                    $data = DB::select("call procPsicologo(NULL)");
                    $Alumnos = $this->getAlumnoData($iIdPsicologo);
                    session(['openAlumnos' => false]);    

                    return View::make('psicologo.index')->with('psicologos', $data)->with('alumnos', $Alumnos)->with('psicologoActual', $iIdPsicologo);
                }                
                $data = DB::select("call procPsicologo(NULL)");
                $psicologo = DB::select("call procPsicologo(?)", [$iIdPsicologo]);
                return View::make('psicologo.index')->with('psicologos', $data)->with('psicologo', $psicologo[0]);
            }
            catch (Throwable $e){
                return '';
            }
        }
        else{
            return Redirect::to('/');
        }
    
    }
    public function openPsicologosAlumnos($iIdPsicologo){
        session(['openAlumnos' => true]);    
        return redirect()->intended('psicologo/'.$iIdPsicologo);
    }

    public function updateAlumnoPsicologos(Request $request){
        
        $iIdPsicologo = $request->input('iIdPsicologo');
        $iIdAlumno = $request->input('iIdAlumno');
        DB::statement("call procAlumnoPsicologoUpdate(?, ?)", [$iIdPsicologo, $iIdAlumno]);
        return $this->openPsicologosAlumnos($iIdPsicologo);
    }

    public function store(Request $request){
        
        try{ 
            $sUserName = $request->input('sUserNamePsicologo');
            $sPassword = strval($request->input('sPasswordPsicologo'));

            $sNombrePsicologo = $request->input('sNombrePsicologo');
            $sApellidoPsicologo = $request->input('sApellidoPsicologo');
            $iTelefono = $request->input('iTelefonoPsicologo');
            $sEmail = $request->input('sEmailPsicologo');
            $bActivo = $request->input('bActivo');


            DB::statement("call procPsicologoABC(0, '".$sUserName."', '".$sPassword."', '".$sNombrePsicologo."', 
                                                    '".$sApellidoPsicologo."', '".$iTelefono."', '".$sEmail."', ".$bActivo.", 0)");
            return Redirect::to('psicologo');
            
            //return View::make('psicologo.index')->with('psicologos', $data);
        }
        catch (Throwable $e){
            return '';
        }

    }

    public function update(Request $request){
        
        try{ 
            $iIdPsicologo = $request->input('iIdPsicologo');
            $sUserName = $request->input('sUserNamePsicologo');
            $sPassword = strval($request->input('sPasswordPsicologo'));

            $sNombrePsicologo = $request->input('sNombrePsicologo');
            $sApellidoPsicologo = $request->input('sApellidoPsicologo');
            $iTelefono = $request->input('iTelefonoPsicologo');
            $sEmail = $request->input('sEmailPsicologo');
            $bActivo = $request->input('bActivo');


            DB::statement("call procPsicologoABC('".$iIdPsicologo."', '".$sUserName."', '".$sPassword."', '".$sNombrePsicologo."', 
                                                        '".$sApellidoPsicologo."', '".$iTelefono."', '".$sEmail."', ".$bActivo.", 0)");
            return Redirect::to('psicologo');
            
            //return View::make('psicologo.index')->with('psicologos', $data);
        }
        catch (Throwable $e){
            return '';
        }
    }

    public function delete($iIdPsicologo){
        DB::statement("call procPsicologoABC(".$iIdPsicologo.", '', '', '', '', '', '', 0, 1)");
        return Redirect::to('psicologo');
    }

    public function loginPsicologoAuth(Request $request): RedirectResponse{
        session(['key' => null]);

        try{ 
            $sUserName = $request->input('sUserNamePsicologo');
            $sPassword = strval($request->input('sPasswordPsicologo'));
            $data = DB::select("call procPsicologoLogin('".$sUserName."', '".$sPassword."')" );
            
            if(count($data) > 0){    
                $request->session()->regenerate();
                session(['key' => $data[0]->iIdPsicologo]);
                return redirect()->intended('psicologo');
               

            }
            return Redirect::to('/');
        }
        catch (Throwable $e){
            return Redirect::to('/');
        }
    }

    public function getAlumnoData($iIdPsicologo){
        try{ 
            $data = DB::select('call procAlumnoPsicologoView(?)', [$iIdPsicologo]);
            return $data;
        }
        catch (Exception $e){
            return '';
        }

    }
    public function loginPsicologo(Request $request){
        try{ 
            $sUserName = $request->input('sUserNamePsicologo');
            $sPassword = strval($request->input('sPasswordPsicologo'));
            $data = DB::select("call procPsicologoLogin('".$sUserName."', '".$sPassword."')" );
            
            if(count($data) > 0){    

                return $data;

            }
            return [];
        }
        catch (Throwable $e){
            return [];
        }
    }
}
