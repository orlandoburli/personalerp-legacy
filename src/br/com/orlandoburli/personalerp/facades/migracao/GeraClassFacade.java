package br.com.orlandoburli.personalerp.facades.migracao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.manutencao.TrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class GeraClassFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	public void execute() {
		read();
		write("\n");
		write("\n");
		write("\n");
		send();
	}
	
	private Class<?> getMyClass() {
		return TrocaManutencaoVO.class;
	}
	
	public void read() {
		Class<?> klass = getMyClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null && field.getAnnotation(Formula.class) == null) {
				if (field.getName().equalsIgnoreCase("isNew") || field.getName().equalsIgnoreCase("serialVersionUID") || field.getName().equalsIgnoreCase("CodigoEmpresaUsuarioLog") || field.getName().equalsIgnoreCase("CodigoLojaUsuarioLog") || field.getName().equalsIgnoreCase("CodigoUsuarioLog"))  {
					continue;
				}
				if (field.getType().equals(Date.class)) {
					write("txt" + field.getName() + ".fullText = FormUtils.readData(data." + klass.getSimpleName().toLowerCase() + "." + field.getName() + ");\n");
				} else if (field.getType().equals(Double.class) || field.getType().equals(BigDecimal.class)) {
					write("txt" + field.getName() + ".fullText = FormUtils.readDecimal(data." + klass.getSimpleName().toLowerCase() + "." + field.getName() + ");\n");
				} else if (field.getType().equals(Timestamp.class)) {
					write("txt" + field.getName() + ".fullText = FormUtils.readHora(data." + klass.getSimpleName().toLowerCase() + "." + field.getName() + ");\n");
				} else {
					write("txt" + field.getName() + ".fullText = data." + klass.getSimpleName().toLowerCase() + "." + field.getName() + ";\n");
				}
			}
		}
	}
	
	public void send() {
		Class<?> klass = getMyClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null && field.getAnnotation(Formula.class) == null) {
				if (field.getName().equalsIgnoreCase("isNew") || field.getName().equalsIgnoreCase("serialVersionUID") || field.getName().equalsIgnoreCase("CodigoEmpresaUsuarioLog") || field.getName().equalsIgnoreCase("CodigoLojaUsuarioLog") || field.getName().equalsIgnoreCase("CodigoUsuarioLog"))  {
					continue;
				}
				if (field.getType().equals(Date.class)) {
					write("\"vo." + field.getName() + "\" :  FormUtils.encode64String(FormUtils.formatData(txt" + field.getName() + ".text)),\n");
				} else {
					write("\"vo." + field.getName() + "\" :  FormUtils.encode64String(txt" + field.getName() + ".text),\n");
				}
			}
		}
	}
	
	public void table() {
		Class<?> klass = getMyClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null) {
				write("" + field.getName().toLowerCase() + "\n");
			}
		}
	}
}