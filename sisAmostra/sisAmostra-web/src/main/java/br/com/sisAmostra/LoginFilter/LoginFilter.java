package br.com.sisAmostra.LoginFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sisAmostra.Entity.Usuario;

/**
 * Servlet Filter implementation class Filter
 */
public class LoginFilter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see LoginFilter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see LoginFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Verifica se a sessão não expirou, se sim volta para a página de login
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (session != null && (session.getAttribute("usuario") != null)) {
			Usuario usuarioLogado =  (Usuario) session.getAttribute("usuario");
			chain.doFilter(request, response);
		} else {
			// Retorna para a página de login
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
		}
	}
	
	/**
	 * @see LoginFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
