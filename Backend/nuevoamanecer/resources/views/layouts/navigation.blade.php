<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Amanecer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
    <div class="container-fluid">
        <div class="row">

            <div class="col-sm-auto bg-light sticky-top">
                <div class="d-flex flex-sm-column flex-row flex-nowrap bg-light align-items-center sticky-top">
                    <ul class="nav nav-pills nav-flush flex-sm-column flex-row flex-nowrap mb-auto mx-auto text-center align-items-center">
                        <li class="nav-item">
                            <a href="{{ URL::to('psicologo') }}" class="nav-link py-3 px-2" title="" data-bs-toggle="tooltip" data-bs-placement="right" data-bs-original-title="Home">
                                <i class="bi bi-person-circle fs-1"></i>
                            </a>
                        </li>
                        <li>
                            <a href="{{ URL::to('Alumno') }}" class="nav-link py-3 px-2" title="" data-bs-toggle="tooltip" data-bs-placement="right" data-bs-original-title="Dashboard">
                                <i class="bi bi-people-fill fs-1"></i>
                            </a>
                        </li>
                    </ul>
                    
                </div>
            </div>

