package br.com.sisAmostra.ManagerBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.sisAmostra.Entity.Usuario;
import br.com.sisAmostra.Service.UsuarioService;

@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginMB {

	private static final String MSG_ERRO_VALIDACAO_SENHA = "Senhas informadas estão diferentes!";
	private static final String MSG_ERRO_VALIDACAO_LOGIN = "Login inválido!";
	
	private String senhaConfirmacao;
	
	private Boolean recuperarSenha = Boolean.FALSE;
	private Boolean alterarSenha = Boolean.FALSE;
	
	Usuario usuario = new Usuario();
	
	@Inject
	UsuarioService usuarioService;
	
	public String validarLogin() {
		Usuario retorno = new Usuario();
		retorno = usuarioService.validarLogin(usuario);
		
		if (retorno.getLogin().equals(usuario.getLogin()) && retorno.getSenha().equals(usuario.getSenha())) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", retorno);
			return "principal?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
			FacesMessage mensagem = new FacesMessage(MSG_ERRO_VALIDACAO_LOGIN);
		    mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, mensagem);
			return null;
		}
	}

	public String alterarSenha() {
		/*try {
			
			if(!senhaConfirmacao.equals(entradaSalvarNovaSenha.getSenhaNova())){
				FacesMessage mensagem = new FacesMessage(MSG_ERRO_VALIDACAO_SENHA);
		        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		        FacesContext.getCurrentInstance().addMessage(null, mensagem);
				return null;
			}
			
			UsuarioLogadoBean usuarioLogado =  (UsuarioLogadoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			
			entradaSalvarNovaSenha.setCdEmpresa(usuarioLogado.getCdEmpresa());
			entradaSalvarNovaSenha.setLogin(usuarioLogado.getLogin());

			entradaSalvarNovaSenha.setUsuarioWebService(usuarioWebService);
			retorno = autenticacaoServiceProxy.alterarSenha(entradaSalvarNovaSenha);
			if (retorno.getErro().getCdErro() == 0) {
				entradaValidarLogin.setCdEmpresa(usuarioLogado.getCdEmpresa());
				entradaValidarLogin.setLogin(usuarioLogado.getLogin());
				entradaValidarLogin.setSenha(entradaSalvarNovaSenha.getSenhaNova());
				validarLogin();
				alterarSenha = Boolean.TRUE;
			}else {
				FacesMessage mensagem = new FacesMessage(retorno.getErro().getDscErro());
		        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		        FacesContext.getCurrentInstance().addMessage(null, mensagem);
				return null;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		return null;
	}

	public String recuperarSenha() {
		/*try {
			entradaRecuperarSenha.setUsuarioWebService(usuarioWebService);
			retorno = autenticacaoServiceProxy.recuperarSenha(entradaRecuperarSenha);
			if (retorno.getErro().getCdErro() == 0) {
				recuperarSenha = Boolean.TRUE;				
			}else {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
				FacesMessage mensagem = new FacesMessage(retorno.getErro().getDscErro());
		        mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		        FacesContext.getCurrentInstance().addMessage(null, mensagem);
				return null;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
		session.invalidate(); 
		return "/paginas/login?faces-redirect=true";
	}
	
	public static String getURLWithContextPath(HttpServletRequest request) {

	   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public Boolean getRecuperarSenha() {
		return recuperarSenha;
	}

	public void setRecuperarSenha(Boolean recuperarSenha) {
		this.recuperarSenha = recuperarSenha;
	}

	public Boolean getAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(Boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}