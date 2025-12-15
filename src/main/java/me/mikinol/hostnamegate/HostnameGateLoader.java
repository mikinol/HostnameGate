package me.mikinol.hostnamegate;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

@SuppressWarnings({"unused", "UnstableApiUsage"})
public final class HostnameGateLoader implements PluginLoader {
    @Override
    public void classloader(PluginClasspathBuilder builder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        resolver.addDependency(
                new Dependency(
                        new DefaultArtifact("org.jetbrains.kotlin:kotlin-stdlib:2.2.21"),
                        null
                )
        );
        resolver.addRepository(
                new RemoteRepository.Builder(
                        "paper",
                        "default",
                        "https://repo.papermc.io/repository/maven-public/"
                ).build()
        );

        builder.addLibrary(resolver);
    }
}

