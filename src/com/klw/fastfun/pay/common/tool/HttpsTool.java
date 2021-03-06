package com.klw.fastfun.pay.common.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsTool {
    private static class TrustAnyTrustManager implements X509TrustManager {
        
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
 
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
 
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
   /* String _url="";
    
    Map<String,String> _params;
    
    public HttpsGetData(String url,Map<String,String> keyValueParams)
    {
        this._url=url;
        this._params=keyValueParams;
    }*/
    public static String sendPost(String urlStr, String httpReq, int timeout) throws Exception {
    	String result = "";
    	BufferedReader in = null;
    	try {
    		
    		SSLContext sc = SSLContext.getInstance("SSL");
    		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
    				new java.security.SecureRandom());
    		URL realUrl = new URL(urlStr);
    		// 打开和URL之间的连接
    		HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
    		//设置https相关属性
    		connection.setReadTimeout(timeout);
    		connection.setConnectTimeout(timeout);
    		connection.setSSLSocketFactory(sc.getSocketFactory());
    		connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
    		connection.setDoOutput(true);
    		
    		connection.setRequestMethod("POST");//POST
    		connection.setDoInput(true);
    		connection.setUseCaches(false);
    		
    		// 设置通用的请求属性
    		connection.setRequestProperty("accept", "*/*");
    		connection.setRequestProperty("connection", "Keep-Alive");
    		connection.setRequestProperty("user-agent",
    				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    		// 写入请求实体
    		connection.getOutputStream().write(httpReq.getBytes());
    		connection.getOutputStream().flush();
    		connection.getOutputStream().close();
    		// 建立实际的连接
    		connection.connect();
    		
    		// 定义 BufferedReader输入流来读取URL的响应
    		in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
    		String line;
    		while ((line = in.readLine()) != null) {
    			result += line;
    		}
    		System.out.println("获取的结果为："+result);
    		connection.disconnect();
    	} catch (Exception e) {
    		System.out.println("发送https请求出现异常！" + e);
    		//e.printStackTrace();
    		throw e;
    	}
    	// 使用finally块来关闭输入流
    	finally {
    		try {
    			if (in != null) {
    				in.close();
    			}
    		} catch (Exception e2) {
    			//e2.printStackTrace();
    			throw e2;
    		}
    	}
    	return result;
    	
    }
    
    public static String sendPost(String urlStr, String httpReq, int timeout, Map<String, String> map) 
    		throws Exception {
         String result = "";
         BufferedReader in = null;
            try {
                
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                         new java.security.SecureRandom());
                URL realUrl = new URL(urlStr);
                // 打开和URL之间的连接
                HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
                //设置https相关属性
                connection.setReadTimeout(timeout);
    			connection.setConnectTimeout(timeout);
                connection.setSSLSocketFactory(sc.getSocketFactory());
                connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
                connection.setDoOutput(true);
                
                connection.setRequestMethod("POST");//POST
    			connection.setDoInput(true);
    			connection.setUseCaches(false);
                
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 设置额外的请求属性
                if (map.size() > 0) {
                	for (String key : map.keySet()) {
                		connection.setRequestProperty(key,map.get(key));
//        				System.out.println(key + "--->" + map.get(key));
                	}
                }
                // 写入请求实体
    			connection.getOutputStream().write(httpReq.getBytes());
    			connection.getOutputStream().flush();
    			connection.getOutputStream().close();
                // 建立实际的连接
                connection.connect();
                
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                System.out.println("获取的结果为："+result);
                connection.disconnect();
            } catch (Exception e) {
                System.out.println("发送https请求出现异常！" + e);
                //e.printStackTrace();
                throw e;
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    //e2.printStackTrace();
                    throw e2;
                }
            }
            return result;
    
    }
    
    public static String sendGet(String urlStr, int timeout) throws Exception
    {
         String result = "";
         BufferedReader in = null;
            try {
                
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                         new java.security.SecureRandom());
                URL realUrl = new URL(urlStr);
                // 打开和URL之间的连接
                HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
                //设置https相关属性
                connection.setReadTimeout(timeout);
    			connection.setConnectTimeout(timeout);
                connection.setSSLSocketFactory(sc.getSocketFactory());
                connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
                connection.setDoOutput(true);
                
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("referer", "http://www.lewanplay.com/");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                System.out.println("获取的结果为："+result);
                connection.disconnect();
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                //e.printStackTrace();
                throw e;
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    //e2.printStackTrace();
                    throw e2;
                }
            }
            return result;
    
    }

    /*private String getParamStr()
    {
        String paramStr="";
        // 获取所有响应头字段
        Map<String, String> params = this._params;
        // 获取参数列表组成参数字符串
        for (String key : params.keySet()) {
            paramStr+=key+"="+params.get(key)+"&";
        }
        //去除最后一个"&"
        paramStr=paramStr.substring(0, paramStr.length()-1);
        
        return paramStr;
    }*/
}
