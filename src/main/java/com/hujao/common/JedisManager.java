package com.hujao.common;

import java.util.HashMap;
import java.util.Map;

import com.hujao.config.ProfileConfig;
import com.hujao.config.RedisConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager {
	static JedisPool pool;
	private final static JedisManager INSTANCE = new JedisManager();

	private JedisManager() {

	}

	public static JedisManager getInstance() {	
		if(pool==null){
			String host =SpringUtil.getBean(RedisConfig.class).getHost().trim();// 控制台显示访问地址
			System.out.println("\n**********************************************************host:"+host);
			
			int port = 6379;
			JedisPoolConfig config = new JedisPoolConfig();
			// 最大空闲连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
			config.setMaxIdle(200);
			// 最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
			config.setMaxTotal(300);
			config.setTestOnBorrow(false);
			config.setTestOnReturn(false);
			String password =SpringUtil.getBean(RedisConfig.class).getPassword().trim();
			pool = new JedisPool(config, host, port, 5000, password);
			
			System.out.println("\n**********************************************************password:"+password);
			
			//System.out.println("开启REDIS pool");
		}
		return INSTANCE;
	}

	protected void finalize() {
		if (pool != null)
			pool.destroy();
	}

	public void Set(String key, String value) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {

			jedis = pool.getResource();
			
			jedis.set(key, value);
			System.out.println("redis set:{" + key + ":" + value + "}");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public void SetEx(String key, String value,int seconds) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.setex(key, seconds, value);
			System.out.println("redis set:{" + key + ":" + value + "}");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void Expire(String key, int seconds) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.expire(key, seconds);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public String Get(String key) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		
		String value = null;
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			value = jedis.get(key);
			if (value == null)
				System.out.println("redis get(" + key + ")=null");
			else
				System.out.println("redis get(" + key + ")=" + value);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	public void Del(String key) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key);
		} catch (Exception ex) {
			ex.printStackTrace();		 
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}
	//存入一个Map
	public void HMSet(String key, HashMap<String, String> map) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hmset(key, map);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	//取Map
	public Map<String, String> HMGet(String key) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	//取Map中的一个key的value
	public String HMGetField(String key, String field) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hget(key, field);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	//设置Map中一个key的value
		public long HMSetField(String key, String field,String value) {
			if(key!=null)
				key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				return jedis.hset(key, field,value);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			return 0;
		}
	//删Map中的一个key
	public void HMDel(String key, String field) {
		if(key!=null)
			key+=SpringUtil.getBean(ProfileConfig.class).getProfile();
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hdel(key, field);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}