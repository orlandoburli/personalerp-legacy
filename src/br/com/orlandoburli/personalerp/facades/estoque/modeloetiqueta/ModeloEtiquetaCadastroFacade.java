package br.com.orlandoburli.personalerp.facades.estoque.modeloetiqueta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.util.IOUtils;

import br.com.orlandoburli.core.dao.estoque.etiqueta.ModeloEtiquetaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloEtiquetaVO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloImpressoraEtiquetaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

@SuppressWarnings("deprecation")
public class ModeloEtiquetaCadastroFacade extends BaseCadastroFlexFacade<ModeloEtiquetaVO, ModeloEtiquetaDAO> {

	private static final long serialVersionUID = 1L;

	private String NomeArquivoTemp;

	public ModeloEtiquetaCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("NomeModeloEtiqueta", "Nome do modelo"));
		addNewValidator(new NotEmptyValidator("CodigoModeloImpressoraEtiqueta", "Impressora"));
	}

	@IgnoreMethodAuthentication
	public void impressoras() {
		try {
			// Lista de impressoras
			write(Utils.voToXml(getGenericDao().getList(new ModeloImpressoraEtiquetaVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doBeforeWriteVo(ModeloEtiquetaVO vo) {
		request.getSession().setAttribute(this.getVOClass().getSimpleName(), vo);
		super.doBeforeWriteVo(vo);
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		String nomeArquivo = System.getProperty("java.io.tmpdir") + File.separator + getNomeArquivoTemp();
		File f = new File(nomeArquivo);

		try {
			// Converte para base64
			
			getVo().setArquivoModeloEtiqueta(Utils.encode64(IOUtils.toByteArray(new FileInputStream(f))));
			
		} catch (FileNotFoundException e) {
			// Se nao existir, busca da sessao
			ModeloEtiquetaVO _vo = (ModeloEtiquetaVO) request.getSession().getAttribute(this.getVOClass().getSimpleName());
			if (_vo != null) {
				getVo().setArquivoModeloEtiqueta(_vo.getArquivoModeloEtiqueta());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doBeforeSave();
	}

	@IgnoreMethodAuthentication
	public void upload() {
		try {
			System.out.println("---------------------");
			System.out.println("INICIANDO UPLOAD");

			String pathUpload = System.getProperty("java.io.tmpdir") + File.separator;

			if (FileUpload.isMultipartContent(request)) {

				File dir = new File(pathUpload);

				if (!dir.exists()) {
					dir.mkdir();
				}

				DiskFileUpload upload = new DiskFileUpload();
				upload.setRepositoryPath(pathUpload);

				try {

					List<?> items = upload.parseRequest(request);
					Iterator<?> it = items.iterator();

					while (it.hasNext()) {

						FileItem fitem = (FileItem) it.next();

						if (!fitem.isFormField()) {

							String finalPathUpload = dir.getAbsolutePath() + File.separator;

							File file = new File(finalPathUpload + getNomeArquivoTemp());
							System.out.println(file.getAbsolutePath());

							FileOutputStream fout = new FileOutputStream(file);
							fout.write(fitem.get());
							fout.close();

							System.out.println("ok");
						}

						fitem.delete();
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
			} else {
				response.getWriter().print("e agora???");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIM UPLOAD");
		System.out.println("---------------------");
	}

	public String getNomeArquivoTemp() {
		return NomeArquivoTemp;
	}

	public void setNomeArquivoTemp(String nomeArquivoTemp) {
		NomeArquivoTemp = nomeArquivoTemp;
	}
}
