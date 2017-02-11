mvn clean install
echo "Clean Install efetuado com sucesso..."
cp -rf ear/target/blog.ear /home/java01/Desktop/jboss/server/default/deploy/
echo "blog.ear atualizado no JBOSS"
