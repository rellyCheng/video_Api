package com.relly.video.common;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动socket服务
 */
@Component
public class ServerRunner implements CommandLineRunner {
    private final SocketIOServer server;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... strings) throws Exception {
        server.start();
    }
}
