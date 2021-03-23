package shopping.redux

import kotlinx.browser.window
import kotlinx.coroutines.launch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction
import shopping.API
import shopping.model.Collection
import shopping.model.DirectoryState
import shopping.model.Section
import shopping.model.State
import shopping.scope

interface DirectoryEvent: RAction

class CollectionsFetchStarted: DirectoryEvent{
  override fun toString() = this::class.simpleName!!
}

data class CollectionsFetchSucceeded(val collections: List<Collection>): DirectoryEvent

data class CollectionsFetchFailed(val exception: Exception): DirectoryEvent

class SectionsFetchStarted: DirectoryEvent{
  override fun toString() = this::class.simpleName!!
}

data class SectionsFetchSucceeded(val sections: List<Section>): DirectoryEvent

data class SectionsFetchFailed(val exception: Exception): DirectoryEvent

fun directoryHandler(state: DirectoryState = DirectoryState(), action: RAction) = when (action) {
  is CollectionsFetchStarted -> state.copy(collectionsFetching = true)
  is CollectionsFetchSucceeded -> state.copy(
    collections = action.collections.map { it.routeName to it }.toMap(),
    collectionsFetching = false
  )
  is CollectionsFetchFailed -> state.copy(collectionsFetching = false)
  is SectionsFetchStarted -> state.copy(sectionsFetching = true)
  is SectionsFetchSucceeded -> state.copy(sections = action.sections, sectionsFetching = false)
  is SectionsFetchFailed -> state.copy(sectionsFetching = false)
  else -> state
}

fun fetchCollections(dispatch: (DirectoryEvent) -> WrapperAction) {
  scope.launch {
    dispatch(CollectionsFetchStarted())
    try {
      dispatch(CollectionsFetchSucceeded(API.getCollections()))
    } catch (e: Exception) {
      dispatch(CollectionsFetchFailed(e))
      window.alert(e.message!!)
    }
  }
}

fun fetchSections(dispatch: (DirectoryEvent) -> WrapperAction) {
  scope.launch {
    dispatch(SectionsFetchStarted())
    try {
      dispatch(SectionsFetchSucceeded(API.getSections()))
    } catch (e: Exception) {
      dispatch(SectionsFetchFailed(e))
      window.alert(e.message!!)
    }
  }
}

fun getSections() = useSelector<State, List<Section>> { it.directory.sections }

fun getCollections() = useSelector<State, Map<String, Collection>> { it.directory.collections }

fun getCollection(name: String) = useSelector<State, Collection?> { it.directory.collections[name] }

fun collectionsFetching() = useSelector<State, Boolean> { it.directory.collectionsFetching }

fun collectionsPresent() = useSelector<State, Boolean> { !it.directory.collections.isNullOrEmpty() }

fun sectionsFetching() = useSelector<State, Boolean> { it.directory.sectionsFetching }
