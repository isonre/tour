package org.lwt.tour.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SetEncodeFilter implements Filter {

        /**
         * ����������������Ϣ
         */
        protected FilterConfig filterConfig = null;

        /**
         * ϵͳȱʡ�����Ա���
         */
        protected String defaultencoding = null;

        /**
         * ��ʼ��������
         */
        public void init(FilterConfig filterConfig) throws ServletException {
                this.filterConfig = filterConfig;
                // ��ȡϵͳȱʡ���Ա���
                this.defaultencoding = filterConfig.getInitParameter("defaultencoding");
        }

        /**
         * ����ϵͳ����������������Ա���
         */
        public void doFilter(
                ServletRequest request,
                ServletResponse response,
                FilterChain chain)
                throws IOException, ServletException {
                //request.setCharacterEncoding(selectEncoding(request));
                request.setCharacterEncoding("GBK");
                chain.doFilter(request, response);
                
        }

        /**
         * ����������
         */
        public void destroy() {
                this.defaultencoding = null;
                this.filterConfig = null;
        }

        /**
         * ѡ����ι�����ʹ�õ����Ա���
         * @param request ����servlet������û�requestʵ��
         * @return ѡ��������Ա���
         */
        protected String selectEncoding(ServletRequest request) {
                // ��������������request��ȡ��session����session�л�ȡ�û���ѡ���encode
                // Ҳ��������������requestȡ���û��ͻ��˵�encode�����õ�ǰ�����Ա���
                return (this.defaultencoding);
        }
}
