package cl.awakelab.newphone

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import cl.awakelab.newphone.model.data.local.list.PhoneDao
import cl.awakelab.newphone.model.data.local.list.PhoneDatabase
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class PhoneRoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var phonesDao: PhoneDao
    private lateinit var db: PhoneDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDatabase::class.java).build()
        phonesDao = db.getPhoneDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertPhones_empty() = runBlocking {
        // Given
        val phonesList = listOf<PhoneEntity>()

        // When
        phonesDao.insertPhone(phonesList)

        // Then A
        val it = phonesDao.getPhones().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertPhones_happyCase_1element() = runBlocking {
        // Given
        val phonesList = listOf(PhoneEntity(1, "www.prueba.cl", "Phone Prueba", 1234))

        // When
        phonesDao.insertPhone(phonesList)

        // Then
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertPhones_happyCase_3elements() = runBlocking {
        // Given
        val phoneList = listOf(PhoneEntity(1,"WWW.imgprueba","Phone1",2345 ), PhoneEntity(2,"WWW.imgprueba1.cl","Phone2",12345 ), PhoneEntity(3,"WWW.imgprueba2.cl","Phone2",122345 ))

        // When
        phonesDao.insertPhone(phoneList)

        // Then
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}