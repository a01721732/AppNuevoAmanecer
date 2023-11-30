<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AlumnoController;
use App\Http\Controllers\PsicologoController;
use Illuminate\Http\Request;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/',  function () {
    return view('login');
});
#region PSICOLOGO

    //----------------------------- GET ------------------------------

    /*Route::get('/psicologo/create',  function () {
        return view('psicologo.create');
    });
    Route::get('/psicologo/edit/{iIdPsicologo}',  [PsicologoController::class, 'edit']);
    */

    //----------------------------- POST ------------------------------
    Route::group(['middleware' => ['web']], function () {
        Route::post('/loginPsicologo',[PsicologoController::class, 'loginPsicologoAuth'] );
        Route::get('/psicologo/{iIdPsicologo?}', [PsicologoController::class, 'index']);

        Route::get('/psicologo/editAlumnosPsicologo/{iIdPsicologo?}', [PsicologoController::class, 'openPsicologosAlumnos']);
        Route::post('/psicologo/updateAlumnosPsicologo', [PsicologoController::class, 'updateAlumnoPsicologos'] );
    });
    
    Route::post('/psicologo/store', [PsicologoController::class, 'store'] );
    Route::post('/psicologo/update', [PsicologoController::class, 'update'] );
    Route::post('/psicologo/delete/{iIdPsicologo}', [PsicologoController::class, 'delete'] );
#endregion

#region ALUMNO

    //----------------------------- GET ------------------------------

    Route::get('/Alumno/{iIdPsicologo?}', [AlumnoController::class, 'index']);
    /*Route::get('/alumno/create',  function () {
        return view('alumno.create');
    });
    Route::get('/alumno/edit/{iIdAlumno}',  [AlumnoController::class, 'edit']);
    */
    //----------------------------- POST ------------------------------
    Route::post('/alumno/store', [AlumnoController::class, 'store'] );
    Route::post('/alumno/update', [AlumnoController::class, 'update'] );
    Route::post('/alumno/delete/{iIdPsicologo}', [AlumnoController::class, 'delete'] );

#endregion

Route::get('/getAlumnoPsicologo/{iIdPsicologo}', [AlumnoController::class, 'getAlumnoData']);

Route::post('/login', [PsicologoController::class, 'loginPsicologo']);

Route::post('/updateNivelAlumno', [AlumnoController::class, 'updateNivelCognitivo']);