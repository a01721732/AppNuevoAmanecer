<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Contracts\Auth\Authenticatable;



class Psicologo extends Model implements Authenticatable
{
    use HasFactory;
    protected $table = 'tpsicologo';
    protected $fillable = ["iIdPsicologo", "sUserNamePsicologo", "sPasswordPsicologo", "sNombrePsicologo", "sApellidoPsicologo", "iTelefonoPsicologo", "sEmailPsicologo", "bActivo"];

    public function getAuthIdentifierName()
    {
        return 'iIdPsicologo';
    }

    public function getAuthIdentifier()
    {
        return $this->getKey();
    }

    public function getAuthPassword()
    {
        return $this->sPasswordPsicologo; 
    }

    public function getRememberToken()
    {
        return $this->remember_token;
    }

    public function setRememberToken($value)
    {
        $this->remember_token = $value;
    }

    public function getRememberTokenName()
    {
        return 'remember_token';
    }
}
