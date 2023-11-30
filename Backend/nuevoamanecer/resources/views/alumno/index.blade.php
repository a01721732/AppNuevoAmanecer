

@include('layouts.navigation')
    <div class="col-sm p-3 min-vh-100">

            <div class="col-sm-3">
                <h1>Alumnos</h1>
            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formAlumnoCrear">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                    </svg>
                </button>
            </div>
    
        <!-- will be used to show any messages -->
        @if (Session::has('message'))
            <div class="alert alert-info">{{ Session::get('message') }}</div>
        @endif
        
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <td>Nombre</td>
                    <td>Apellido</td>
                    <td>Edad</td>
                    <td>Nivel Cognitivo</td>
                    <td>Activo</td>
                </tr>
            </thead>
            <tbody>
            @foreach($alumnos as $key => $value)
                <tr>
                    <td>{{ $value->sNombreAlumno }}</td>
                    <td>{{ $value->sApellidoAlumno }}</td>
                    <td>{{ $value->iEdad }}</td>
                    <td>{{ $value->sNivelCognitivo }}</td>
                    <td>{{ $value->bActivo }}</td>
        
                    <!-- we will also add show, edit, and delete buttons -->
                    <td>
                        <!-- edit this shark (uses the edit method found at GET /sharks/{id}/edit -->
                        <a class="btn btn-small btn-info" href="{{ URL::to('Alumno/'.$value->iIdAlumno) }}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </a>
                        {{ Form::open(array('url' => 'alumno/delete/'.$value->iIdAlumno, 'class' => 'pull-right')) }}
                        {{ Form::hidden('_method', 'POST') }}
                        {{ Form::button('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                        </svg>', ['type' => 'submit', 'class' => 'btn btn-warning']) }}
                        {{ Form::close() }}
        
                    </td>
                </tr>
            @endforeach
            </tbody>
        </table>
    
    </div>
</div>


<!-- ------------------------------ MODAL ------------------------------- -->

<div class="modal fade" id="formAlumnoCrear" tabindex="-1" aria-labelledby="formAlumnoLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="formAlumnoLabel">Registrar Alumnos</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            {{ HTML::ul($errors->all()) }}

            {{ Form::open(array('url' => 'alumno/store')) }}

                <div class="form-group">
                    {{ Form::label('sNombreAlumnoLabel', 'Nombre Alumno') }}
                    {{ Form::text('sNombreAlumno') }}
                </div>

                <div class="form-group">
                    {{ Form::label('sApellidoAlumnoLabel', 'Apellido Alumno') }}
                    {{ Form::text('sApellidoAlumno') }}
                </div>

                <div class="form-group">
                    {{ Form::label('iEdadAlumnoLabel', 'Edad') }}
                    {{ Form::number('iEdad')  }}
                </div>
                
                <div class="form-group">
                    {{ Form::label('iNivelCognitivo', 'Nivel Cognitivo') }}
                    {{ Form::select('iNivelCognitivo', array(0 => 'Selecciona Nivel Cognitivo', 1 => 'Nivel Cognitivo 1', 2 => 'Nivel Cognitivo 2', 
                                    3 => 'Nivel Cognitivo 3', 4 => 'Nivel Cognitivo 4'), 0);  }}
                </div>
                <div class="form-group">
                    {{ Form::label('bActivoLabel', 'Activo') }}
                    {{ Form::checkbox('bActivo', 1, true) }}
                </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          {{ Form::submit('Registrar', ['class' => 'btn btn-primary']) }}

          {{ Form::close() }}
        </div>
      </div>
    </div>
  </div>

  
  @if(!empty($alumno->iIdAlumno))

    <script> 
        $(window).on('load', function() {
            if(window.location != "{{  URL::to('Alumno') }}" ){
                $("#formAlumnoEditar").modal("show");
            }
            const button = document.getElementById('closeModalButton');
            button.addEventListener('click', function handleClick() {
                window.location.href = "{{  URL::to('Alumno') }}"
                $("#formAlumnoEditar").modal("hide");
            });

        });


    </script>
    <div class="modal" id="formAlumnoEditar" tabindex="-1" aria-labelledby="formAlumnoEditarLabel" aria-hidden="false">
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="formAlumnoEditarLabel">Editar Alumno </h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                {{ HTML::ul($errors->all()) }}

                {{ Form::open(array('url' => 'alumno/update')) }}
                    {{ Form::hidden('iIdAlumno', $alumno->iIdAlumno) }}
                    
                    <div class="form-group">
                        {{ Form::label('sNombreAlumnoLabel', 'Nombre Alumno') }}
                        {{ Form::text('sNombreAlumno', $alumno->sNombreAlumno) }}
                    </div>
                
                    <div class="form-group">
                        {{ Form::label('sApellidoAlumnoLabel', 'Apellido Alumno') }}
                        {{ Form::text('sApellidoAlumno', $alumno->sApellidoAlumno) }}
                    </div>
                
                    <div class="form-group">
                        {{ Form::label('iEdadAlumnoLabel', 'Edad') }}
                        {{ Form::number('iEdad', $alumno->iEdad)  }}
                    </div>
                
                    <div class="form-group">
                        {{ Form::label('iNivelCognitivo', 'Nivel Cognitivo') }}
                        {{ Form::number('iNivelCognitivo', $alumno->sNivelCognitivo)  }}
                    </div>
                    <div class="form-group">
                        {{ Form::label('bActivoLabel', 'Activo') }}
                        {{ Form::checkbox('bActivo', 1, true) }}
                    </div>
                


            </div>
            <div class="modal-footer">
               
                <a  id="closeModalButton"  class="btn btn-secondary"  >Close</a>
              
                {{ Form::submit('Editar', ['class' => 'btn btn-primary']) }}


                {{ Form::close() }}
            </div>
        </div>
        </div>
    </div>

  @endif


</body>
</html>