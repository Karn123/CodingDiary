package service.common.impl;

import daos.BaseDAO;
import framework.exceptions.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import service.common.ICommService;

import javax.annotation.Resource;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.StringReader;

@Service(value="commService")
public class CommServiceImpl implements ICommService {

	private Log log = LogFactory.getLog(CommServiceImpl.class);

	@Resource
	protected BaseDAO baseDAO;

	@Override
	public <T> T getEntity(String id, Class<T> cls) throws ServiceException {
		// TODO Auto-generated method stub
		T t=baseDAO.findById(Long.parseLong(id), cls);
		return t;
	}

	public String handlerHTML(String html)throws IOException {
		HtmlParser htmlParser =new HtmlParser();
		htmlParser.parse(html);
		return htmlParser.getText();
	}

	public class HtmlParser extends HTMLEditorKit.ParserCallback {
		StringBuffer s;

		public HtmlParser() {}

		public void parse(String html) throws IOException {
			s = new StringBuffer();
			ParserDelegator delegator = new ParserDelegator();
			StringReader in=new StringReader(html);
			// the third parameter is TRUE to ignore charset directive
			delegator.parse(in, this, Boolean.TRUE);
		}

		public void handleText(char[] text, int pos) {
			s.append(text);
		}

		public String getText() {
			return s.toString();
		}
	}
}
