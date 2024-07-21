import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appsv.turngame.data.local.room.history.Converters
import com.appsv.turngame.data.local.room.history.GameHistory
import com.appsv.turngame.data.local.room.history.GameHistoryDao

@Database(entities = [GameHistory::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameHistoryDao(): GameHistoryDao
}
