package digital.wup.cardsummary.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.paperdb.Book
import io.paperdb.Paper
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PaperModule {

    companion object {
        const val CARD_BOOK_NAME = "cards"
    }

    @Provides
    @Singleton
    fun provideCardsBook(@ApplicationContext context: Context): Book {
        Paper.init(context)
        return Paper.book()
    }
}