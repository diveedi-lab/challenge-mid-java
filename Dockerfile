FROM debian:buster-slim

ARG version=8.392.08-1

RUN set -eux \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
        curl ca-certificates gnupg software-properties-common fontconfig java-common \
    && curl -fL https://apt.corretto.aws/corretto.key | apt-key add - \
    && add-apt-repository 'deb https://apt.corretto.aws stable main' \
    && mkdir -p /usr/share/man/man1 || true \
    && apt-get update \
    && apt-get install -y java-1.8.0-amazon-corretto-jdk=1:$version maven \
    && apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false \
        curl gnupg software-properties-common

ARG UID=1000
ARG GID=1000

RUN groupadd -g "${GID}" challenger \
  && useradd -m --no-log-init -u "${UID}" -g "${GID}" challenger
USER challenger

ENV LANG C.UTF-8
ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-amazon-corretto
ENV MAVEN_HOME=/usr/share/maven

ENV PATH=$MAVEN_HOME/bin:$PATH
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

COPY . /app

EXPOSE 9080

ENTRYPOINT [ "/app/entrypoint.sh" ]
