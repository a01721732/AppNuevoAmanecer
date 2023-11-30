<!DOCTYPE html>
<html>
    <header>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <script src="https://code.jquery.com/jquery-3.7.1.slim.min.js" integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>    
    </header>
    <body>
        <div id="template-bg-1" class="bg-info">
            
            <div class="d-flex flex-column min-vh-100 justify-content-center align-items-center ">
                <div class="card p-4 mb-5 bg-warning">
                        <div class="text-center">
                            <h3>Nuevo Amanecer</h3>
                        </div>
                        <div class="card-body w-100">
                        {{ HTML::ul($errors->all()) }}

                        {{ Form::open(array('url' => 'loginPsicologo')) }}

                            <div class="form-group">
                                <div class="input-group form-group mt-3">
                                    <div class="bg-secondary rounded-start">
                                        <span class="m-3"><i
                                            class="fas fa-user mt-2"></i></span>
                                    </div>
                                {{ Form::text('sUserNamePsicologo', null,  ['class' => 'form-control', 'placeholder' => 'Usuario/Correo']) }}
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group form-group mt-3">
                                    <div class="bg-secondary rounded-start">
                                        <span class="m-3"><i
                                            class="fas fa-user mt-2"></i></span>
                                    </div>
                                {{ Form::password('sPasswordPsicologo', ['class' => 'form-control', 'placeholder' => 'Contaseña']) }}
                                </div>
                            </div>
                            <div class="form-groupv  mt-3">
                                {{ Form::submit('Iniciar Sesion', ['class' => 'btn bg-secondary float-end text-white w-100']) }}

                                
                            </div>
                            {{ Form::close() }}
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>