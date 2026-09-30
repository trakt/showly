package com.michaldrabik.data_remote

import com.michaldrabik.data_remote.aws.AwsRemoteDataSource
import com.michaldrabik.data_remote.tmdb.TmdbRemoteDataSource
import com.michaldrabik.data_remote.trakt.TraktRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides external data sources access points.
 */
interface RemoteDataSource {
  val trakt: TraktRemoteDataSource
  val aws: AwsRemoteDataSource
  val tmdb: TmdbRemoteDataSource
}

@Singleton
internal class MainRemoteDataSource @Inject constructor(
  override val trakt: TraktRemoteDataSource,
  override val tmdb: TmdbRemoteDataSource,
  override val aws: AwsRemoteDataSource,
) : RemoteDataSource
