public class Usuario {
    String nombreUsuario;
    String contraseña;
    String tipodePlan;

    public Usuario(String nombreUsuario, String contraseña, String tipodePlan) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipodePlan = tipodePlan;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getTipodePlan() {
        return tipodePlan;
    }
    public void setTipodePlan(String tipodePlan) {
        this.tipodePlan = tipodePlan;
    }
}
