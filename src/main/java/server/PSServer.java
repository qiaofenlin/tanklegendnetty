package server;


import config.Configurator;
import config.Log4JConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class PSServer {
    private int port;
    private String host;

    private PSServer(){

    }

    private void init() throws Exception {
        Configurator.init();
        Log4JConfig.load();
        Thread listener = new Thread(new ConsoleListener());
        listener.start();
    }

    public static PSServer newInstance() throws Exception {
        PSServer psServer = new PSServer();
        psServer.init();
        return psServer;
    }

    public PSServer bind(String host,int port){
        this.host = host;
        this.port = port;
        return this;
    }

    public void run(){
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workgroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();

            server.group(bossgroup, workgroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 102480)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializerImp());

            ChannelFuture future = server.bind(host,port).sync();

            future.channel().closeFuture().sync();
        }catch (InterruptedException e) {
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }
    }

    public void close(){

    }
}

