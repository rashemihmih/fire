import java.util.Random;

@SuppressWarnings("MagicNumber")
public class ParticleSystem extends Xform {
    private final Random random = new Random();
    static int emitterRadius = 30;
    double horizontalVelocity = 0.2;
    double verticalVelocity = 1;

    public void loop() {
        for (int i = 0; i < Settings.getEmitterPower(); i++) {
            addParticle();
        }
        getChildren().forEach(node -> ((Particle) node).move());
        getChildren().removeIf(node -> (!((Particle) node).isAlive()));
    }

    @SuppressWarnings("OverlyComplexBooleanExpression")
    public void addParticle() {
        final double x = emitterRadius * random.nextGaussian() * 0.5;
        final double y = emitterRadius * random.nextGaussian() * 0.5;
        final Vector3D location = new Vector3D(x, y, 0);
        double vx = horizontalVelocity * random.nextGaussian();
        double vy = horizontalVelocity * random.nextGaussian();
        final int initialLife = (int) (Particle.MAX_LIFE / 3 + Particle.MAX_LIFE / 3 * 2 * Math.random());
        if ((x > emitterRadius && vx > 0) || (x < emitterRadius && vx < 0)) {
            vx = -vx;
        }
        if ((y > emitterRadius && vy > 0) || (y < emitterRadius && vy < 0)) {
            vy = -vy;
        }
        final double vz = verticalVelocity;
        final Vector3D velocity = new Vector3D(vx, vy, vz);
        getChildren().add(new Particle(location, velocity, initialLife));
    }

    public int getSize() {
        return getChildren().size();
    }
}
