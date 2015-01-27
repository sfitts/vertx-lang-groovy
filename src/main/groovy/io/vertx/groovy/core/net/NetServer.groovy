/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.core.net;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.groovy.core.metrics.Measured
import java.util.Map
import io.vertx.core.json.JsonObject
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
/**
 * Represents a TCP server
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@CompileStatic
public class NetServer implements Measured {
  final def io.vertx.core.net.NetServer delegate;
  public NetServer(io.vertx.core.net.NetServer delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * The metric base name
   *
   * @return the metric base name
   */
  public String metricBaseName() {
    def ret = ((io.vertx.core.metrics.Measured) this.delegate).metricBaseName();
    return ret;
  }
  /**
   * Will return the metrics that correspond with this measured object.
   *
   * @return the map of metrics where the key is the name of the metric (excluding the base name) and the value is
   * the json data representing that metric
   */
  public Map<String, Map<String, Object>> metrics() {
    def ret = ((io.vertx.core.metrics.Measured) this.delegate).metrics()?.collectEntries({k, v -> [k, v.getMap()]});
    return ret;
  }
  /**
   * Return the connect stream for this server. The server can only have at most one handler at any one time.
   * As the server accepts TCP or SSL connections it creates an instance of {@link NetSocket} and passes it to the
   * connect stream {@link ReadStream#handler(io.vertx.core.Handler)}.
   *
   * @return the connect stream
   */
  public NetSocketStream connectStream() {
    def ret= NetSocketStream.FACTORY.apply(this.delegate.connectStream());
    return ret;
  }
  /**
   * Supply a connect handler for this server. The server can only have at most one connect handler at any one time.
   * As the server accepts TCP or SSL connections it creates an instance of {@link NetSocket} and passes it to the
   * connect handler.
   *
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer connectHandler(Handler<NetSocket> handler) {
    def ret= NetServer.FACTORY.apply(this.delegate.connectHandler(new Handler<io.vertx.core.net.NetSocket>() {
      public void handle(io.vertx.core.net.NetSocket event) {
        handler.handle(NetSocket.FACTORY.apply(event));
      }
    }));
    return ret;
  }
  /**
   * Start listening on the port and host as configured in the {@link io.vertx.core.net.NetServerOptions} used when
   * creating the server.
   * <p>
   * The server may not be listening until some time after the call to listen has returned.
   *
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen() {
    this.delegate.listen();
    return this;
  }
  /**
   * Like {@link #listen} but providing a handler that will be notified when the server is listening, or fails.
   *
   * @param listenHandler  handler that will be notified when listening or failed
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen(Handler<AsyncResult<NetServer>> listenHandler) {
    this.delegate.listen(new Handler<AsyncResult<io.vertx.core.net.NetServer>>() {
      public void handle(AsyncResult<io.vertx.core.net.NetServer> event) {
        AsyncResult<NetServer> f
        if (event.succeeded()) {
          f = InternalHelper.<NetServer>result(new NetServer(event.result()))
        } else {
          f = InternalHelper.<NetServer>failure(event.cause())
        }
        listenHandler.handle(f)
      }
    });
    return this;
  }
  /**
   * Start listening on the specified port and host, ignoring post and host configured in the {@link io.vertx.core.net.NetServerOptions} used when
   * creating the server.
   * <p>
   * Port {@code 0} can be specified meaning "choose an random port".
   * <p>
   * Host {@code 0.0.0.0} can be specified meaning "listen on all available interfaces".
   * <p>
   * The server may not be listening until some time after the call to listen has returned.
   *
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen(int port, String host) {
    this.delegate.listen(port, host);
    return this;
  }
  /**
   * Like {@link #listen(int, String)} but providing a handler that will be notified when the server is listening, or fails.
   *
   * @param port  the port to listen on
   * @param host  the host to listen on
   * @param listenHandler handler that will be notified when listening or failed
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen(int port, String host, Handler<AsyncResult<NetServer>> listenHandler) {
    this.delegate.listen(port, host, new Handler<AsyncResult<io.vertx.core.net.NetServer>>() {
      public void handle(AsyncResult<io.vertx.core.net.NetServer> event) {
        AsyncResult<NetServer> f
        if (event.succeeded()) {
          f = InternalHelper.<NetServer>result(new NetServer(event.result()))
        } else {
          f = InternalHelper.<NetServer>failure(event.cause())
        }
        listenHandler.handle(f)
      }
    });
    return this;
  }
  /**
   * Start listening on the specified port and host "0.0.0.0", ignoring post and host configured in the
   * {@link io.vertx.core.net.NetServerOptions} used when creating the server.
   * <p>
   * Port {@code 0} can be specified meaning "choose an random port".
   * <p>
   * The server may not be listening until some time after the call to listen has returned.
   *
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen(int port) {
    this.delegate.listen(port);
    return this;
  }
  /**
   * Like {@link #listen(int)} but providing a handler that will be notified when the server is listening, or fails.
   *
   * @param port  the port to listen on
   * @param listenHandler handler that will be notified when listening or failed
   * @return a reference to this, so the API can be used fluently
   */
  public NetServer listen(int port, Handler<AsyncResult<NetServer>> listenHandler) {
    this.delegate.listen(port, new Handler<AsyncResult<io.vertx.core.net.NetServer>>() {
      public void handle(AsyncResult<io.vertx.core.net.NetServer> event) {
        AsyncResult<NetServer> f
        if (event.succeeded()) {
          f = InternalHelper.<NetServer>result(new NetServer(event.result()))
        } else {
          f = InternalHelper.<NetServer>failure(event.cause())
        }
        listenHandler.handle(f)
      }
    });
    return this;
  }
  /**
   * Close the server. This will close any currently open connections. The close may not complete until after this
   * method has returned.
   */
  public void close() {
    this.delegate.close();
  }
  /**
   * Like {@link #close} but supplying a handler that will be notified when close is complete.
   *
   * @param completionHandler  the handler
   */
  public void close(Handler<AsyncResult<Void>> completionHandler) {
    this.delegate.close(completionHandler);
  }
  /**
   * The actual port the server is listening on. This is useful if you bound the server specifying 0 as port number
   * signifying an ephemeral port
   *
   * @return the actual port the server is listening on.
   */
  public int actualPort() {
    def ret = this.delegate.actualPort();
    return ret;
  }

  static final java.util.function.Function<io.vertx.core.net.NetServer, NetServer> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.core.net.NetServer arg -> new NetServer(arg);
  };
}
