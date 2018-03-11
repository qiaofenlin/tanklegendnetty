package server;


import dao.UserPlayInfo;
import handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


public class ChannelInitializerImp extends ChannelInitializer<NioSocketChannel> {
    private UserPlayInfo userPlayInfo;

    public ChannelInitializerImp(UserPlayInfo userPlayInfo) {this.userPlayInfo=userPlayInfo;
    }

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        channel.pipeline().addLast(new HttpRequestDecoder());//inbound
        channel.pipeline().addLast(new HttpObjectAggregator(65536));//inbound
        channel.pipeline().addLast(new HttpResponseEncoder());//outbound
        channel.pipeline().addLast(new HttpHeadHandler());//outbound
        channel.pipeline().addLast(new HttpContentHandler());//inbound
        channel.pipeline().addLast(new LoginHandler(userPlayInfo));//inbound outbound
        channel.pipeline().addLast(new TankHandler());//inbound outbound
        channel.pipeline().addLast(new MapHandler());//inbound outbound
        channel.pipeline().addLast(new TankCodeHandler());//inbound outbound
        channel.pipeline().addLast(new TradeUserInfoGetHandler());//inbound outbound

    }
}
