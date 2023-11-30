@include('layouts.navigation')
    <div class="col-sm p-3 min-vh-100">
            <div class="col-sm-3">
                        <h1>Psicólogos</h1>
                        <div class="col-sm-1">

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formPsicologoCrear">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
   
        

            <!-- will be used to show any messages -->
            @if (Session::has('message'))
                <div class="alert alert-info">{{ Session::get('message') }}</div>
            @endif

            {{ Form::hidden('iIdPsicologo', 'secret') }}

            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <td>Nombre</td>
                        <td>Apellido</td>
                        <td>Email</td>
                        <td>Telefono</td>
                    </tr>
                </thead>
                <tbody>
                @foreach($psicologos as $key => $value)
                    <tr>
                        <td>{{ $value->sNombrePsicologo }}</td>
                        <td>{{ $value->sApellidoPsicologo }}</td>
                        <td>{{ $value->sEmailPsicologo }}</td>
                        <td>{{ $value->iTelefonoPsicologo }}</td>


                        <td>
                            
                            <a class="btn btn-small btn-info" href="{{ URL::to('psicologo/'.$value->iIdPsicologo) }}">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                            {{ Form::open(array('url' => 'psicologo/delete/'.$value->iIdPsicologo, 'class' => 'pull-right')) }}
                            {{ Form::hidden('_method', 'POST') }}
                            {{ Form::button('<i class="bi bi-trash"></i>', ['type' => 'submit', 'class' => 'btn btn-warning']) }}
                            {{ Form::close() }}
                            <a class="btn btn-small btn-info" href="{{ URL::to('/psicologo/editAlumnosPsicologo/'.$value->iIdPsicologo) }}">
                                <i class="bi bi-people-fill"></i>
                            </a>
                        </td>
                    </tr>
                @endforeach
                </tbody>
            </table>
    </div>
</div>

<!-- ------------------------------ MODAL ------------------------------- -->

<div class="modal fade" id="formPsicologoCrear" tabindex="-1" aria-labelledby="formPsicologoLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="formPsicologoLabel">Registrar Psicologo</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            {{ HTML::ul($errors->all()) }}

            {{ Form::open(array('url' => 'psicologo/store')) }}
            
                <div class="form-group">
                    {{ Form::label('sNombrePsicologoLabel', 'Nombre Psicologo') }}
                    {{ Form::text('sNombrePsicologo') }}
                </div>
            
                <div class="form-group">
                    {{ Form::label('sApellidoPsicologoLabel', 'Apellido') }}
                    {{ Form::text('sApellidoPsicologo') }}
                </div>
            
                <div class="form-group">
                    {{ Form::label('sUserNameLabel', 'Usuario') }}
                    {{ Form::text('sUserNamePsicologo') }}
                </div>   
                <div class="form-group">
                    {{ Form::label('sPasswordLabel', 'Contraseña') }}
                    {{ Form::password('sPasswordPsicologo') }}
                </div>
            
                <div class="form-group">
                    {{ Form::label('sEmailPsicologoLabel', 'Email') }}
                    {{ Form::email('sEmailPsicologo') }}
                </div>
                
                <div class="form-group">
                    {{ Form::label('iTelefonoPsicologoLabel', 'Telefono') }}
                    {{ Form::number('iTelefonoPsicologo')  }}
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

  
@if(!empty($psicologo->iIdPsicologo))

    <script> 
        $(window).on('load', function() {
            if(window.location != "{{  URL::to('psicologo') }}" ){
                $("#formPsicologoEditar").modal("show");
            }
            const button = document.getElementById('closeModalButton');
            button.addEventListener('click', function handleClick() {
                window.location.href = "{{  URL::to('psicologo') }}"
                $("#formPsicologoEditar").modal("hide");
            });

        });


    </script>
    <div class="modal" id="formPsicologoEditar" tabindex="-1" aria-labelledby="formPsicologoEditarLabel" aria-hidden="false">
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="formPsicologoEditarLabel">Editar Psicologo</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                {{ HTML::ul($errors->all()) }}

                {{ Form::open(array('url' => 'psicologo/update')) }}
                    {{ Form::hidden('iIdPsicologo', $psicologo->iIdPsicologo) }}
                    <div class="form-group">
                        {{ Form::label('sNombrePsicologoLabel', 'Nombre Psicologo') }}
                        {{ Form::text('sNombrePsicologo', $psicologo->sNombrePsicologo) }}
                    </div>

                    <div class="form-group">
                        {{ Form::label('sApellidoPsicologoLabel', 'Apellido Psicologo') }}
                        {{ Form::text('sApellidoPsicologo', $psicologo->sApellidoPsicologo) }}
                    </div>

                    <div class="form-group">
                        {{ Form::label('sUserNameLabel', 'Usuario') }}
                        {{ Form::text('sUserNamePsicologo', $psicologo->sUserNamePsicologo) }}
                    </div>   
                    <div class="form-group">
                        {{ Form::label('sPasswordLabel', 'Contraseña') }}
                        {{ Form::password('sPasswordPsicologo')}}
                    </div>

                    <div class="form-group">
                        {{ Form::label('sEmailPsicologoLabel', 'Email') }}
                        {{ Form::email('sEmailPsicologo', $psicologo->sEmailPsicologo) }}
                    </div>
                    
                    <div class="form-group">
                        {{ Form::label('iTelefonoPsicologoLabel', 'Telefono') }}
                        {{ Form::number('iTelefonoPsicologo', $psicologo->iTelefonoPsicologo)  }}
                    </div>
                    <div class="form-group">
                        {{ Form::label('bActivoLabel', 'Activo') }}
                        {{ Form::checkbox('bActivo', 1, $psicologo->bActivo) }}
                    </div>


            </div>
            <div class="modal-footer">
                <!--<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>-->
                
                <a  id="closeModalButton"  class="btn btn-secondary"  >Close</a>
              
                {{ Form::submit('Editar', ['class' => 'btn btn-primary']) }}


                {{ Form::close() }}
            </div>
        </div>
        </div>
    </div>

@endif


@if(!empty($alumnos))
<script> 
    $(window).on('load', function() {
        if(window.location != "{{  URL::to('psicologo') }}" ){
            $("#alumnosTableModal").modal("show");
        }
        const button = document.getElementById('closeModalButton');
        button.addEventListener('click', function handleClick() {
            window.location.href = "{{  URL::to('psicologo') }}"
            $("#alumnosTableModal").modal("hide");
        });

        
        $(".checkbox").change(function() {

            $.ajax({
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: {"_token": "{{ csrf_token() }}", "iIdPsicologo": "{{$psicologoActual}}", "iIdAlumno": this.value},
                type: 'POST',
                dataType: 'json',
                url: "{{ URL::to('psicologo/updateAlumnosPsicologo')}}",
                async:true

            });
           

    });
});


</script>
<div class="modal" id="alumnosTableModal" tabindex="-1" aria-labelledby="alumnosTableModalLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="alumnosTableModalLabel">Alumnos</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <td>Nombre</td>
                            <td>Apellido</td>                            
                        </tr>
                    </thead>
                    <tbody>
                    @foreach($alumnos as $key => $value)
                        <tr>
                            <td>{{ $value->sNombre }}</td>
                            <td>{{ $value->sApellido }}</td>
                            <td>
                                <input class="checkbox" type="checkbox" id="cbox{{$value->iIdAlumno }}" value="{{$value->iIdAlumno }}" @if( $value->bPsicologo  == 1 ){ checked }@endif />
                            </td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>


            </div>
            <div class="modal-footer">            
                <a  id="closeModalButton"  class="btn btn-secondary"  >Close</a>     
            </div>
        </div>
        </div>
    </div>
@endif
</body>
</html>