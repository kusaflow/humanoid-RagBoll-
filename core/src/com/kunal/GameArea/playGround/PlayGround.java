package com.kunal.GameArea.playGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kunal.AllVariables;
import com.kunal.EveryMenu.TempMenu;
import com.kunal.MainGame;
import com.kunal.utils.BodyGeneraton;

import java.time.chrono.MinguoChronology;

public class PlayGround implements Screen{

    private MainGame game;
    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera cam;
    private Viewport port;

    //body creation module
    objectCreation objcre;
    //player moves
    Player_moves player_moves;

    public PlayGround(MainGame game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, game.WIDTH/2, game.HEIGHT/2);

        port = new FitViewport(game.WIDTH/2, game.HEIGHT/2, cam);

        world = new World(new Vector2(0,-10f), false);

        b2dr = new Box2DDebugRenderer();

        objcre = new objectCreation(world);
        objcre.bodycreatation();
        objcre.joints();

        player_moves = new Player_moves();

    }



    @Override
    public void show() {
        AllVariables.stuntStep =0;
        AllVariables.BackFlip = false;
        AllVariables.shouldmoveToNextStep = false;
    }

    @Override
    public void render(float dt) {
        update(dt);
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, cam.combined.scl(game.PPM));

    }

    private void update(float dt) {
        input();

        if(AllVariables.BalancingPlayer)
            player_moves.balancer();


        //update of world
        //if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            world.step(1 / ((1/dt)* MainGame.refreshRate), 6, 2);;
        game.batch.setProjectionMatrix(cam.combined);


        //camera Update
        Vector3 campos = cam.position;
        campos.x = AllVariables.head.getPosition().x*game.PPM;
        campos.y = AllVariables.head.getPosition().y*game.PPM;
        cam.position.set(campos);
        cam.update();

    }
    Boolean t = false;

    private void input() {
        float velx =0, vely =0;
        float velx2 =0, vely2 =0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velx -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velx +=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.S)){
            vely -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.W)){
            vely +=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velx2 -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velx2 +=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            vely2 -=1;
        }if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            vely2 +=1;
        }

        AllVariables.tester.setLinearVelocity(velx, vely);
        AllVariables.tester2.setLinearVelocity(velx2, vely2);

        if(Gdx.input.isKeyJustPressed(Input.Keys.B))
            game.setScreen(new TempMenu(game));



        if (Gdx.input.isKeyPressed(Input.Keys.M)
                  || Gdx.input.justTouched()
                ){
            t=true;
            //player_moves.walking();
            player_moves.tester();
            //player_moves.backflip();
            player_moves.running();

        }
        if (t){
            //player_moves.walkingWithBigSteps();
            //player_moves.walkingimproper();
            //player_moves.running();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            AllVariables.BalancingPlayer = false;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            AllVariables.BalancingPlayer = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.T)){
            AllVariables.stuntStep = 0;
            AllVariables.BackFlip = false;
        }

            //moves like flips
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
            AllVariables.stuntStep = 0;
            AllVariables.BackFlip = true;
            AllVariables.shouldmoveToNextStep = false;
        }

        if(AllVariables.BackFlip)
            player_moves.backflip();


    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
        world.dispose();
        b2dr.dispose();
    }
}
