package io.vertx.lang.groovy
/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ResolveSamePackageVerticleClass extends GroovyVerticle {

    @Override
    void start() throws Exception {
        SamePackage o = new SamePackage()
        DeploymentTest.started.set(true)
    }

    @Override
    void stop() throws Exception {
        DeploymentTest.stopped.set(true)
    }
}
