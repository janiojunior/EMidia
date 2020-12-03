package br.unitins.emidia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.emidia.model.Perfil;
import br.unitins.emidia.model.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/adm/*"})
public class SecurityFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
// 	 	Para desabilitar o filter, descomente as duas proximas linhas e comente o restante		
		chain.doFilter(request, response);
		return;
		
//		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		// imprime o endereco da pagina
//		String endereco = servletRequest.getRequestURI();
//		System.out.println(endereco);
//
//		// retorna a sessao corrente (false - para nao criar uma nova sessao)
//		HttpSession session = servletRequest.getSession(false);
//		
//		Usuario usuario = null;
//		if (session != null)
//			usuario = (Usuario) session.getAttribute("usuarioLogado");
//		
//		if (usuario == null) {
//			((HttpServletResponse) response).sendRedirect("/EMidia/faces/login.xhtml");
//		}  else {
//			// nesse local podemos trabalhar as permissoes por pagina
//			if (Perfil.ADMINISTRADOR.equals(usuario.getPerfil())) {
//				// segue o fluxo (permitido)
//				chain.doFilter(request, response);
//				return;
//			} else {
//				((HttpServletResponse) response).sendRedirect("/EMidia/faces/login.xhtml");
//			}
//			
//		}
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		System.out.println("Security Filter Funcionando.");
	}

}
